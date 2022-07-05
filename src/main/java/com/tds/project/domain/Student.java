package com.tds.project.domain;

import com.alibaba.excel.annotation.ExcelProperty;

public class Student {

    @ExcelProperty(value = "id",index = 0)
    private String id;

    @ExcelProperty(value = "姓名",index = 1)
    private String a;

    @ExcelProperty(value = "年龄",index = 2)
    private String b;

    @ExcelProperty(value = "性别",index = 3)
    private String cc;

    @ExcelProperty(value = "学校",index = 4)
    private String d;

    @ExcelProperty(value = "地址",index = 5)
    private String e;

    @ExcelProperty(value = "时间",index = 6)
    private String f;

    private String wjmc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", cc='" + cc + '\'' +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", f='" + f + '\'' +
                ", wjmc='" + wjmc + '\'' +
                '}';
    }
}
