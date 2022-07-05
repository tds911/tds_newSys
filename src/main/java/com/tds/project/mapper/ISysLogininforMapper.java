package com.tds.project.mapper;


import com.tds.project.domain.SysLogininfor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysLogininforMapper {

    public void insertLogininfor(SysLogininfor loginimfor);
}
