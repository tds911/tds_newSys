package com.tds.project.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.tds.common.utils.DateUtils;
import com.tds.project.domain.StudentinfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.project.mapper.StudentinfoMapper;
import com.tds.project.domain.Studentinfo;
import com.tds.project.service.StudentinfoService;

/**
 * 学生信息Service业务层处理
 *
 * @author tds
 * @date 2022-07-22
 */
@Service
public class StudentinfoServiceImpl implements StudentinfoService {
    @Autowired
    private StudentinfoMapper studentinfoMapper;


    /**
     * 查询学生信息
     *
     * @param pkValue 学生信息ID
     * @return 学生信息;
     */
    @Override
    public Studentinfo selectStudentinfoById(String pkValue) {
        return studentinfoMapper.selectStudentinfoById(pkValue);
    }

    /**
     * 查询学生信息列表
     *
     * @param studentinfoForm 学生信息
     * @return 学生信息
     */
    @Override
    public List<Studentinfo> selectStudentinfoList(StudentinfoForm studentinfoForm) {
        PageHelper.startPage(studentinfoForm.getPageNum(),studentinfoForm.getPageSize());
        return studentinfoMapper.selectStudentinfoList(studentinfoForm);
    }


    /**
     * 新增学生信息
     *
     * @param studentinfo 学生信息
     * @return 结果
     */
    @Override
    public int insertStudentinfo(Studentinfo studentinfo) {
        studentinfo.setCreateTime(DateUtils.getNowDate());
        return studentinfoMapper.insertStudentinfo(studentinfo);
    }

    /**
     * 修改学生信息
     *
     * @param studentinfo 学生信息
     * @return 结果
     */
    @Override
    public int updateStudentinfo(Studentinfo studentinfo) {
        studentinfo.setUpdateTime(DateUtils.getNowDate());
        return studentinfoMapper.updateStudentinfo(studentinfo);
    }

    /**
     * 批量删除学生信息
     *
     * @param 需要删除的学生信息
     * @return 结果
     */
    @Override
    public int deleteStudentinfoByIds(String[] pkValues) {
        return studentinfoMapper.deleteStudentinfoByIds(pkValues);
    }

    /**
     * 删除学生信息信息
     *
     * @param pkValue 学生信息ID
     * @return 结果
     */
    @Override
    public int deleteStudentinfoById(String pkValue) {
        return studentinfoMapper.deleteStudentinfoById(pkValue);
    }
}