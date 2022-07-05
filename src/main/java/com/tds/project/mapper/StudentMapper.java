package com.tds.project.mapper;


import com.tds.project.domain.Student;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {



    public Integer save(@Param("list") List<Student> list);

    int select(Student student);
    Student selectById(String id);

   Map selectzdz(String id);

   List<String> selectsfcz(@Param("list") List<String>list);

    List selectAll(Student student);

    int insert(Student student);
}
