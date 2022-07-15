package com.tds.project.service;

import java.util.List;

import com.tds.project.domain.DictType;

/**
 * 字典类型Service接口
 * @author tds
 * @date 2022-07-15
 */

public interface DictTypeService {

    /**
     * 查询字典类型
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public DictType selectDictTypeById(Long dictId);

    /**
     * 查询字典类型列表
     *
     * @param dictType 字典类型
     * @return 字典类型集合
     */
    public List<DictType> selectDictTypeList(DictType dictType);

    /**
     * 新增 字典类型
     *
     * @param dictType 字典类型
     * @return 结果
     */
    public int insertDictType(DictType dictType);

    /**
     * 修改 字典类型
     *
     * @param dictType 字典类型
     * @return 结果
     */

    public int updateDictType(DictType dictType);

    /**
     * 删除字典类型
     *
     * @param dictId 字典类型ID
     * @return 结果
     */

    public int deleteDictTypeById(Long dictId);

    /**
     * 批量删除字典类型
     *
     * @param dictId需要删除的ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictIds);

    public List<DictType> selectDictTypeAll();
}