package com.tds.project.controller;

import java.util.List;

import com.tds.common.web.page.TableDataInfo;
import com.tds.project.domain.StudentinfoForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tds.project.domain.Studentinfo;
import com.tds.project.service.StudentinfoService;
import com.tds.common.web.controller.BaseController;
import com.tds.common.web.domain.server.AjaxResult;

/**
 * 学生信息Controller
 *
 * @authortds
 * @date2022-07-22
 */
@RestController
@RequestMapping("/Student/studentinfo")
public class StudentinfoController extends BaseController {
    @Autowired
    private StudentinfoService studentinfoService;

    /**
     * 查询学生信息列表
     */

    @PostMapping("/list")
    public TableDataInfo list(@RequestBody StudentinfoForm studentinfoForm){
        List<Studentinfo> list = studentinfoService.selectStudentinfoList(studentinfoForm);
        return getDataTable(list);
    }

    /**
     * 获取学生信息详细信息
     */

    @GetMapping(value = "/{pkValue}")
    public AjaxResult getInfo(@PathVariable("pkValue") String pkValue) {
        return AjaxResult.success(studentinfoService.selectStudentinfoById(pkValue));
    }

    /**
     * 新增学生信息
     */

    @PostMapping("/add")
    public AjaxResult add(@RequestBody Studentinfo studentinfo) {
        return toAjax(studentinfoService.insertStudentinfo(studentinfo));
    }

    /**
     * 修改学生信息
     */

    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Studentinfo studentinfo) {
        return toAjax(studentinfoService.updateStudentinfo(studentinfo));
    }

    /**
     * 删除学生信息
     */

    @DeleteMapping("/{pkValues}")
    public AjaxResult remove(@PathVariable String[] pkValues) {
        return toAjax(studentinfoService.deleteStudentinfoByIds(pkValues));
    }
}