package com.tds.project.service.impl;

import com.tds.common.constant.UserConstants;

import com.tds.common.utils.SecurityUtils;
import com.tds.common.utils.StringUtils;
import com.tds.common.web.domain.TreeSelect;

import com.tds.project.domain.SysMenu;
import com.tds.project.domain.SysUser;
import com.tds.project.mapper.ISysMenuMapper;
import com.tds.project.mapper.SysRoleMenuMapper;
import com.tds.project.service.ISysMenuService;
import com.tds.project.vo.MetaVo;
import com.tds.project.vo.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ISysMenuServiceImpl implements ISysMenuService {
    @Autowired
    private ISysMenuMapper iSysMenuMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = iSysMenuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 查询系统菜单列表
     * @param menu
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        List<SysMenu> menus = null;
        if (SysUser.isAdmin(userId)) {
            menus = iSysMenuMapper.selectMenuList(menu);
        } else {
            menu.getParams().put("userId", userId);
            menus = iSysMenuMapper.selectMenuListByUserId(menu);
        }
        return menus;
    }

    @Override
    public List<SysMenu> selectMenuList(Long userId) {
        return selectMenuList(new SysMenu(), userId);
    }

    /**
     * 根据用户Id查询菜单
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(userId)) {
            menus = iSysMenuMapper.selectMenuTreeAll();
        } else {
            menus = iSysMenuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    @Override
    public List<Integer> selectMenuListByRoleId(Long roleId) {
        return iSysMenuMapper.selectMenuListByRoleId(roleId);
    }

    /**
     * 构建前端所需要的菜单
     * @param menus
     * @return
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<SysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需树结构
     * @param menus
     * @return
     */
    @Override
    public List<SysMenu> buildMenusTress(List<SysMenu> menus) {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext();) {
            SysMenu t = (SysMenu) iterator.next();
            if (t.getParentId() == 0) {
                recursionFn(menus, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 构建前端所需下拉树结构
     * @param menus
     * @return
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus) {
        List<SysMenu> menuTrees = buildMenusTress(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return iSysMenuMapper.selectMenuById(menuId);
    }

    /**
     * 是否存在菜单子节点
     * @param menuId
     * @return
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        int result = iSysMenuMapper.hasChildByMenuId(menuId);
        return result > 0 ? true : false;
    }

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        int result = roleMenuMapper.checkMenuExistRole(menuId);
        return result > 0 ? true : false;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return iSysMenuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return iSysMenuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return iSysMenuMapper.deleteMenuById(menuId);
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu info = iSysMenuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    public String getRouteName(SysMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;

        }
        return routerName;
    }

    public String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType()) && UserConstants.NOT_UNIQUE.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        } else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    public String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    public boolean isMenuFrame(SysMenu menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType()) && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();) {
            SysMenu t = (SysMenu) iterator.next();
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    private void recursionFn(List<SysMenu> list, SysMenu t) {
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu sysMenu : childList) {
            if (hasChild(list, sysMenu)) {
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenu n = (SysMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext()) {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
