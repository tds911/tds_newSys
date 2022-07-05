package com.tds.project.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import com.tds.project.domain.Student;
import com.tds.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DemoDataListstener extends AnalysisEventListener<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListstener.class);

    /**
     * 每个5条数据存入数据库
     *
     * @param student
     * @param analysisContext
     */
    private static final int BATCH_COUNT = 1;
    List<Student> list = new ArrayList<Student>(BATCH_COUNT);


    private StudentService studentService;


    public DemoDataListstener(StudentService studentService) {
        this.studentService = studentService;

    }

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(student));
        System.out.println(student.toString());
        String id = student.getId();
        System.out.println(id);
        int count = studentService.select(student);
        if (count == 0) {
            list.add(student);
            System.out.println(list);
            if (list.size() >= BATCH_COUNT) {
                saveDate();
                list.clear();
            }
        }else {
            System.out.println("数据存在");
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveDate();
        LOGGER.info("所有数据解析完成！");
    }

    private void saveDate() {
        LOGGER.info("{}条数据开始保存");
        studentService.save(list);
        LOGGER.info("数据存储成功");
    }


}
