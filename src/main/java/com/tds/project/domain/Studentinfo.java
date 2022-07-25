package com.tds.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tds.common.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学生信息对象studentinfo
 *
 * @author:tds
 * @date:2022-07-22
 */
public class Studentinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String pkValue;
    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;
    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private String age;
    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;
    /**
     * 班级
     */
    @Excel(name = "班级")
    private String classs;
    /**
     * 专业
     */
    @Excel(name = "专业")
    private String major;
    /**
     * 学号
     */
    @Excel(name = "学号")
    private String studentid;
    /**
     * 0 辍学，1在读，2毕业，3开除
     */
    @Excel(name = "0 辍学，1在读，2毕业，3开除")
    private String status;
    /**
     * 家庭地址
     */
    @Excel(name = "家庭地址")
    private String address;
    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phone;
    /**
     * 出生日期
     */
    @Excel(name = "出生日期")
    private String birthdate;
    /**
     * $column.columnComment
     */
    private String createBy;
    /**
     * $column.columnComment
     */
    private Date createTime;
    /**
     * $column.columnComment
     */
    private String updateBy;
    /**
     * $column.columnComment
     */
    private Date updateTime;
    /**
     * $column.columnComment
     */
    @Excel(name = "出生日期")
    private String remark;
    private String college;
    private String pageNum;
    private String pageSize;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setPkValue(String pkValue) {
        this.pkValue = pkValue;
    }

    public String getPkValue() {
        return pkValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setClass(String classs) {
        this.classs = classs;
    }

    public String getClasss() {
        return classs;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return "Studentinfo{" +
                "pkValue='" + pkValue + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", classs='" + classs + '\'' +
                ", major='" + major + '\'' +
                ", studentid='" + studentid + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", college='" + college + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                '}';
    }
}