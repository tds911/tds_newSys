package com.tds.project.mapper;


import com.tds.project.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper {

    User findByUsername(String username);
}
