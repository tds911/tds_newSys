package com.tds.project.service.impl;


import com.tds.project.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ISysRoleServiceImpl implements ISysRoleService {
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        return null;
    }
}
