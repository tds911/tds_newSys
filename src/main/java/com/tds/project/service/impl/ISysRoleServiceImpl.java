package com.tds.project.service.impl;


import com.tds.project.mapper.SysRoleMenuMapper;
import com.tds.project.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ISysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        Set<String> perms=new HashSet<String>();
        List<String> stringList = roleMenuMapper.selectMenuPermsByUserId(userId);
        perms.addAll(stringList);
        return perms;
    }
}
