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


public class DemoDataListstener1 extends AnalysisEventListener<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListstener1.class);

    /**
     * 每个5条数据存入数据库
     *
     * @param student
     * @param analysisContext
     */
    private static final int BATCH_COUNT = 1;
    List<Student> list = new ArrayList<Student>(BATCH_COUNT);

    List<String> list2=new ArrayList<String>();
    private StudentService studentService;
    private Map<Integer, String> headMap;
    private List<String> listid = new ArrayList<String>();

    public Map<Integer, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(Map<Integer, String> headMap) {
        this.headMap = headMap;
    }

    public DemoDataListstener1(StudentService studentService) {
        this.studentService = studentService;

    }


    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(student));

        list.add(student);
        select();
        String b = student.getB();
        String f = student.getF();
        list2.add(b);
        list2.add(f);
        List<String> selectsfcz = studentService.selectsfcz(list2);
        boolean contains = selectsfcz.contains(b);
        boolean contains1 = selectsfcz.contains(f);
        System.out.println(contains);
        System.out.println(contains1);

        list2.clear();
        if (list.size() >= BATCH_COUNT) {

            System.out.println("list集合是========》"+list);
            saveDate();


            list.clear();
        }



    }

    public void select() {
        for (Student student1 : list) {
            listid.add(student1.getB());
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("所有数据解析完成！");

//        String max = Collections.max(listid);
//        System.out.println("=============>");
//        String s = listid.get(0);
//        Map map = studentService.selectzdz(s);


    }

    public void saveDate() {

        LOGGER.info("{}条数据开始保存");
        studentService.save(list);
        LOGGER.info("数据存储成功");


    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap=headMap;
        System.out.println(headMap);
        if(("id").equals(headMap.get(0))&&("姓名").equals(headMap.get(1))&&("年龄").equals(headMap.get(2))){
            System.out.println("满足");
        }else {
            System.out.println("不满足");
        }
//        if (headMap.get(0).equals("id") && headMap.get(1).equals("姓名") && headMap.get(2).equals("年龄")) {
//            System.out.println("满足");
//        } else {
//            System.out.println("不满足");
//        }


    }
}
