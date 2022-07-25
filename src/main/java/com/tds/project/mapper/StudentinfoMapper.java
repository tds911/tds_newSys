package com.tds.project.mapper;

import java.util.List;

import com.tds.project.domain.Studentinfo;
import com.tds.project.domain.StudentinfoForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 学生信息Mapper接口
 * @author tds
 * @date 2022-07-22
 */
@Mapper
public interface StudentinfoMapper {

    /**
     * 查询学生信息
     *
     * @param pkValue 学生信息ID
     * @return 学生信息
     */
    public Studentinfo selectStudentinfoById(String pkValue);

    /**
     * 查询学生信息列表
     *
     * @param  studentinfoForm 学生信息
     * @return 学生信息集合
     */
    public List<Studentinfo> selectStudentinfoList(@Param("params") StudentinfoForm studentinfoForm);

    /**
     *新增 学生信息
     *
     * @param studentinfo 学生信息
     * @return 结果
     */
    public int insertStudentinfo(Studentinfo studentinfo);

    /**
     * 修改 学生信息
     *
     * @param studentinfo 学生信息
     * @return 结果
     */

    public int updateStudentinfo(Studentinfo studentinfo);

    /**
     * 删除学生信息
     *
     * @param pkValue 学生信息ID
     * @return 结果
     */

    public int deleteStudentinfoById(String pkValue);

    /**
     * 批量删除学生信息
     *
     * @param pkValue需要删除的ID
     * @return 结果
     */
    public int deleteStudentinfoByIds(String[] pkValues);
}