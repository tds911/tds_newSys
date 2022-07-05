package com.tds.project.mapper;


import com.tds.project.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISysMenuMapper {
    public List<String> selectMenuPermsByUserId(Long userId);

    public List<SysMenu>selectMenuList(SysMenu menu);

    public List<SysMenu> selectMenuListByUserId(SysMenu menu);

    public List<String> selectMenuPerms();

    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    public List<SysMenu> selectMenuTreeAll();

    public List<Integer> selectMenuListByRoleId(Long roleId);

    public SysMenu selectMenuById(Long menuId);

    public int hasChildByMenuId(Long menuId);

    public int insertMenu(SysMenu menu);

    public int updateMenu(SysMenu menu);

    public int deleteMenuById(Long menuId);

    public SysMenu checkMenuNameUnique(@Param("menuName")String menuName,@Param("parentId")Long parentId);
}
