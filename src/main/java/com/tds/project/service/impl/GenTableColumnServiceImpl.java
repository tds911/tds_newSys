package com.tds.project.service.impl;


import com.tds.project.domain.GenTableColumn;
import com.tds.project.mapper.GenTableColumnMapper;
import com.tds.project.service.GenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenTableColumnServiceImpl implements GenTableColumnService {
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
        return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
    }
}
