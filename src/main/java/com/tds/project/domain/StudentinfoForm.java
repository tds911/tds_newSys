package com.tds.project.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class StudentinfoForm extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String pubParam;//模糊查询字段
    private int pageNum =1;
    private int pageSize =5;

}
