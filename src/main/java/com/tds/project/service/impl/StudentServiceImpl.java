package com.tds.project.service.impl;

import com.deepoove.poi.XWPFTemplate;
import com.tds.project.domain.ExportData;
import com.tds.project.domain.Student;

import com.tds.project.mapper.StudentMapper;
import com.tds.project.service.StudentService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

@Service
public class StudentServiceImpl implements StudentService {
   @Autowired
   private StudentMapper studentMapper;

    @Override
    public Integer save(List<Student> list) {
      return studentMapper.save(list);
    }

    @Override
    public int select(Student student) {
        return studentMapper.select(student);
    }

    @Override
    public List selectAll(Student student) {
        return studentMapper.selectAll(student);
    }

    @Override
    public Student selectById(String id) {
        return studentMapper.selectById(id);
    }


    @Override
    public List<String> selectsfcz(List<String> list) {
        return studentMapper.selectsfcz(list);
    }

    @Override
    public List<ExportData> export(Student student) {
        List<ExportData> list = new ArrayList<>();
        List<Student> result=studentMapper.selectAll(student);
        for (Student student1 : result) {

            ExportData exportData = new ExportData(student1.getId(),
                    student1.getA(),
                    student1.getB(),
                    student1.getCc(),
                    student1.getD(),
                    student1.getE(),
                    student1.getF());
            list.add(exportData);
        }
        return list;
    }




    /**
     * 压缩成ZIP 方法
     * @param srcFiles 需要压缩的文件列表
     * @param out           压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    @Override
    public  void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("压缩失败",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveWj(String newWJMC,String filePath) {
        File file =new File(filePath+newWJMC);
        long length = file.length();
    }

    @Override
    public int insert(Student student) {
        int count=studentMapper.insert(student);
        SaveWj(student);
        return count;
    }

    @Override
    public String export(String id) {
        Student student=this.selectById(id);

        String filePath = "D:/测试/模板/ceshi";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try{
            XWPFTemplate template=getTemplate(student);
            FileOutputStream out=new FileOutputStream(filePath+"/"+student.getWjmc()+".doc");
            template.write(out);
            out.flush();
            out.close();
            out.close();
            template.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "D:/测试/模板/ceshi/"+student.getWjmc()+".doc";
    }


    public void SaveWj(Student student) {
        //String newWJMC = student.getWjmc() + ".doc";
        XWPFTemplate template = getTemplate(student);
        try {

        String filePath = "D:/测试/模板/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + student.getWjmc() + ".doc");
        template.write(out);
        out.flush();
        out.close();
        template.close();

        //新增时增加文件数据

        }catch (Exception e){
        e.printStackTrace();
          }

    }


    public XWPFTemplate getTemplate(Student student){

        XWPFTemplate template=XWPFTemplate.compile("D:/测试/模板.docx").render(new
                HashMap<String,Object>(){{
                    put("id",student.getId());
                    put("a",student.getA());
                    put("b",student.getB());
                    put("cc",student.getCc());
                    put("d",student.getD());
                    put("e",student.getE());
                    put("f",student.getF());
                    put("wjmc",student.getWjmc());
                }});
        return template;
    }


}
