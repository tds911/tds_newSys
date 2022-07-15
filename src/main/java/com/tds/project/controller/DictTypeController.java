package com.tds.project.controller;

import java.util.List;

import com.tds.common.web.page.TableDataInfo;
import com.tds.project.domain.DictType;
import com.tds.project.service.DictTypeService;
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

import com.tds.common.web.controller.BaseController;
import com.tds.common.web.domain.server.AjaxResult;

/**
 * 字典类型Controller
 *
 * @authortds
 * @date2022-07-15
 */
@RestController
@RequestMapping("/system/dict/type")
public class DictTypeController extends BaseController {
    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 查询字典类型列表
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('null:null:list')")
    @GetMapping("/list")
    public TableDataInfo list(DictType dictType) {
        startPage();
        List<DictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 获取字典类型详细信息
     */
    @PreAuthorize("@ss.haspermi('null:null:list')")
    @GetMapping(value = "/{dictId}")
    public AjaxResult getInfo(@PathVariable("dictId") Long dictId) {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('null:null:list')")
    @PostMapping
    public AjaxResult add(@RequestBody DictType dictType) {
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize("@ss.hasPermi('null:null:list')")
    @PutMapping
    public AjaxResult edit(@RequestBody DictType dictType) {
        return toAjax(dictTypeService.updateDictType(dictType));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('null:null:list')")
    @DeleteMapping("/{dictIds}")
    public AjaxResult remove(@PathVariable Long[] dictIds) {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    @GetMapping("/optionselect")
    public AjaxResult optionselect(){
        List<DictType> dictTypes = dictTypeService.selectDictTypeAll();
        return AjaxResult.success(dictTypes);
    }
}