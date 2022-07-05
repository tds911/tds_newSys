package com.tds.project.service;

import java.util.Set;


public interface ISysRoleService {
    public Set<String> selectRolePermissionByUserId(Long userId);
}
