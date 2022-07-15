package com.tds.project.service.impl;
import java.util.List;

import com.tds.common.utils.DateUtils;
import com.tds.project.mapper.DictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.project.domain.DictType;
import com.tds.project.service.DictTypeService;

/**
 * 字典类型Service业务层处理
 * @author tds
 * @date 2022-07-15
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;


    /**
     * 查询字典类型
     * @param dictId 字典类型ID
     * @return 字典类型;
     */
    @Override
    public DictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 查询字典类型列表
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public List<DictType> selectDictTypeList(DictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }


    /**
     * 新增字典类型
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public int insertDictType(DictType dictType) {
dictType.setCreateTime(DateUtils.getNowDate());
        return dictTypeMapper.insertDictType(dictType);
    }

    /**
     * 修改字典类型
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public int updateDictType(DictType dictType) {
dictType.setUpdateTime(DateUtils.getNowDate());
        return dictTypeMapper.updateDictType(dictType);
    }

    /**
     * 批量删除字典类型
     * @param ${pkCplumn.javaField}s 需要删除的字典类型ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(Long[] dictIds){
        return dictTypeMapper.deleteDictTypeByIds(dictIds);
    }

    @Override
    public List<DictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
    * 删除字典类型信息
     * @param dictId 字典类型ID
     * @return 结果
*/
    @Override
    public int deleteDictTypeById(Long dictId){
        return dictTypeMapper.deleteDictTypeById(dictId);
    }
}