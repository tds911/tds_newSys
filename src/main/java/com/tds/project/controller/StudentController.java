package com.tds.project.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.tds.project.domain.ExportData;
import com.tds.project.domain.Student;

import com.tds.project.listen.DemoDataListstener;
import com.tds.project.listen.DemoDataListstener1;
import com.tds.project.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

;

@RestController
public class StudentController {
    @Resource
    private StudentService studentService;

    @PostMapping("/excel/uploads")
    public void uploads(MultipartFile[] file, String ajbh) {
        for (int i = 0; i < file.length; i++) {
            MultipartFile fileUpload = file[i];
            String fileName = fileUpload.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filepath = "D:\\测试";
            String w = ajbh + "/" + fileName;
            try {
                File file1 = new File(new File(filepath).getAbsolutePath() + "/" + w);
                if (!file1.getParentFile().exists()) {
                    file1.getParentFile().mkdir();
                }
                fileUpload.transferTo(file1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("上传失败");
            }

        }
    }

    /**
     * EasyExcel导入
     * @param fileList
     * @param ajbh
     */
    @PostMapping("/inserts")
    public void insertA(@RequestParam("file") MultipartFile[] fileList, @RequestParam(value = "ajbh") String ajbh) {
        for (int j = 0; j < fileList.length; j++) {
//            String fileName = fileList[j].getOriginalFilename();
            try {
                InputStream readpath1 = fileList[j].getInputStream();

                /**
                 * 注意这里获取sheet会改变File获取流的类型
                 */
                ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(readpath1);
                ExcelReader excelReader = excelReaderBuilder.build();
                List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();

                /**
                 * 重新接创建输入流
                 */
                for (int i = 0; i <= sheets.size(); i++) {
                    InputStream readpath = fileList[j].getInputStream();
                    EasyExcelFactory.read(readpath, Student.class, new DemoDataListstener1(studentService)).sheet(i).doRead();


                    readpath.close();
                }

                System.out.println("读取完成");
                readpath1.close();

            } catch (IOException e) {
                e.printStackTrace();

            }


        }

    }

    @GetMapping("/insertsNew")
    public void insertsNew() {

//            String fileName = fileList[j].getOriginalFilename();
        try {
            InputStream readpath = new FileInputStream(new File("D:\\测试\\学生.xlsx"));

            String filePath = "D:\\测试\\学生.xlsx";
            ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(readpath);

            ExcelReader excelReader = excelReaderBuilder.build();

            List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();

//                for (int i = 0; i <= sheets.size(); i++) {
            ExcelReaderBuilder read = EasyExcelFactory.read(readpath, Student.class, new DemoDataListstener(studentService));

            ExcelReaderSheetBuilder excelReaderSheetBuilder = read.sheet(0);
            excelReaderSheetBuilder.doRead();
//                }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @PostMapping("/list")
    public List selectAll(Student student) {
        return studentService.selectAll(student);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, Student student) throws IOException {
//        response.setContentType("application/vnd.ms-excel");
        response.setContentType("application/octet-stream");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        //  response.setCharacterEncoding("UTF-8");

        ServletOutputStream out = null;
        String excelName = java.net.URLEncoder.encode("测试", "UTF-8");
        System.out.println(excelName);


        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + excelName + ".xlsx");
        out = response.getOutputStream();

        EasyExcelFactory.write(out, ExportData.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("测试").doWrite(studentService.export(student));

    }

    /**
     * easyExcel导出
     * @param student
     */
    @PostMapping("/export1")
    public void export(Student student) {
        String fileName = "D:\\springboot学习\\Excel3\\" + "测试" + System.currentTimeMillis() + ".xlsx";

        // EasyExcelFactory.write(fileName,ExportData.class).sheet("数据").doWrite(studentService.export(student));
        EasyExcel.write(fileName, ExportData.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("测试").doWrite(studentService.export(student));
    }

    @RequestMapping("/DownloadZip")
    public void zip() {

        List<File> versionZip = new ArrayList<>();
        //需要压缩文件地址
        versionZip.add(new File("D:\\测试\\学生.xlsx"));
        versionZip.add(new File("D:\\测试\\捕获.PNG"));
         versionZip.add(new File("D:\\测试\\out2.xlsx"));
        //压缩文件保存地址以及文件名
        String filePath="D:\\测试\\";
        long time = System.currentTimeMillis();
        String fileZip = filePath+time+"压缩包.zip";
        OutputStream os = null;
        try {
            os = new FileOutputStream(fileZip);
            //调用压缩方法
            studentService.toZip(versionZip, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 导入word文件
     * @param student
     */
    @PostMapping("/add")
    public void add(@RequestBody Student student){
        studentService.insert(student);
    }

    /**
     * 下载word文件
     * @param id
     * @return
     */
    @GetMapping("export/{id}")
    public String download(@PathVariable String id){
        return studentService.export(id);
    }
}
