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
import java.util.Map;


public class DemoDataListstener2 extends AnalysisEventListener<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListstener2.class);

    /**
     * 每个5条数据存入数据库
     *
     * @param student
     * @param analysisContext
     */
    private static final int BATCH_COUNT = 1;
    List<Student> list = new ArrayList<Student>(BATCH_COUNT);


    private StudentService studentService;
    private Map<Integer, String> headMap;
    private List<String> listid = new ArrayList<String>();

    public Map<Integer, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(Map<Integer, String> headMap) {
        this.headMap = headMap;
    }




    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(student));




    }

    public void select() {
        for (Student student1 : list) {

            listid.add(student1.getId());
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("所有数据解析完成！");



    }



    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap = headMap;
    }
}
