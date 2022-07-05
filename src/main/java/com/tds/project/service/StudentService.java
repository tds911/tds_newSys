package com.tds.project.service;



import com.tds.project.domain.ExportData;
import com.tds.project.domain.Student;


import java.io.File;
import java.io.OutputStream;
import java.util.List;

public interface StudentService {


    Integer save(List<Student> list);

    int select(Student student);

    List selectAll(Student student);

    Student selectById(String id);

    List<String> selectsfcz(List<String> list);

    List<ExportData> export(Student student);

    public  void toZip(List<File> srcFiles , OutputStream out);

    void saveWj(String newWJMC,String filePath);

    int insert(Student student);

    String export(String id);


}
