package com.wsy.webseed.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wsy.webseed.controller.pojo.tool.BootstrapTreeviewPojo;
import com.wsy.webseed.dao.SysMenuDao;
import com.wsy.webseed.dao.SysRoleDao;
import com.wsy.webseed.dao.SysRoleMenuDao;
import com.wsy.webseed.dao.SysUserDao;
import com.wsy.webseed.dao.SysUserRoleDao;
import com.wsy.webseed.dao.entity.SysMenu;
import com.wsy.webseed.dao.entity.SysRoleMenu;
import com.wsy.webseed.dao.entity.SysUser;
import com.wsy.webseed.dao.entity.SysUserRole;
import com.wsy.webseed.service.SysMenuService;
import com.wsy.webseed.util.DataTableFormate;
import com.wsy.webseed.util.Pagination;

@Service("sysMenuService")
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    SysMenuDao                  sysMenuDao;

    @Autowired
    SysUserDao                  sysUserDao;

    @Autowired
    SysUserRoleDao              sysUserRoleDao;

    @Autowired
    SysRoleDao                  sysRoleDao;

    @Autowired
    SysRoleMenuDao              sysRoleMenuDao;

    @Override
    public List<SysMenu> query(String loginId) {
        List<SysMenu> beanList = queryByLoginId(loginId);
        beanList = filterMenuByType(beanList, 1);
        List<SysMenu> list = initIdxMenuTree(beanList, 0);
        return list;
    }

    /*
     * 递归算法
     */
    private List<SysMenu> initIdxMenuTree(List<SysMenu> beanList, Integer pid) {
        List<SysMenu> list = new ArrayList<SysMenu>();
        for (SysMenu bean : beanList) {
            if (pid == bean.getPid()) {
                list.add(bean);
            }
        }
        if (list != null && !list.isEmpty()) {
            for (SysMenu bean : list) {
                List<SysMenu> childList = initIdxMenuTree(beanList, bean.getId());
                if (childList != null && !childList.isEmpty()) {
                    bean.setHasChild(true);
                    bean.setChildList(childList);
                }
            }
        }
        return list;
    }

    /**
     * 过滤menu对象，1是菜单，2是按钮
     * 
     * @param list
     * @param type
     * @return
     */
    private List<SysMenu> filterMenuByType(List<SysMenu> list, int type) {
        List<SysMenu> filtedMenus = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : list) {
            if (sysMenu.getType() == type) {
                filtedMenus.add(sysMenu);
            }
        }

        return filtedMenus;
    }

    private List<SysMenu> queryByLoginId(String loginId) {
        SysUser curUser = sysUserDao.query(loginId);
        if (curUser == null) {
            LOGGER.warn("User {} not config.", loginId);
            return null;
        }
        List<SysUserRole> sysUserRoleList = sysUserRoleDao.queryByUserId(curUser.getId());
        if (sysUserRoleList == null || sysUserRoleList.isEmpty()) {
            LOGGER.warn("User {} not assign role.", loginId);
            return null;
        }
        List<Integer> roleIdList = new ArrayList<Integer>();
        for (SysUserRole userRole : sysUserRoleList) {
            roleIdList.add(userRole.getRoleId());
        }
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuDao.queryByRoleIds(roleIdList);
        if (sysRoleMenuList == null || sysRoleMenuList.isEmpty()) {
            LOGGER.warn("User {} not assign menu.", loginId);
            return null;
        }
        List<Integer> menuIds = new ArrayList<Integer>();
        for (SysRoleMenu roleMenu : sysRoleMenuList) {
            menuIds.add(roleMenu.getMenuId());
        }
        List<SysMenu> beanList = sysMenuDao.query(menuIds);
        return beanList;
    }

    @Override
    public List<BootstrapTreeviewPojo> queryBootstrapTreeviewPojos(String loginName) {
        return _queryBootstrapTreeviewPojos(query(loginName));
    }

    private List<BootstrapTreeviewPojo> _queryBootstrapTreeviewPojos(List<SysMenu> menus) {
        List<BootstrapTreeviewPojo> pojos = new ArrayList<BootstrapTreeviewPojo>();
        if(menus == null)return pojos;
        for (SysMenu menu : menus) {
            BootstrapTreeviewPojo pojo = new BootstrapTreeviewPojo();
            pojo.setText(menu.getName());
            pojo.setHref(menu.getUrl());
            List<SysMenu> children = menu.getChildList();
            if (children != null && children.size() > 0) {
                pojo.setChildren(_queryBootstrapTreeviewPojos(children));
                pojo.setHasChildren(true);
            }

            pojos.add(pojo);
        }
        return pojos;
    }

    @Override
    public String queryNotTreeMenus(Pagination<SysMenu> pagination) {
        String json = "";
        List<SysMenu> data = sysMenuDao.query(pagination);
        ;
        if (pagination == null) {
            json = DataTableFormate.formateTableJsonStrNoPagination(data, data.get(0).getSimplePropertyPreFilter());
        } else {
            pagination.setData(data);
            int total = sysMenuDao.queryCount();
            pagination.setRecordsTotal(total);
            pagination.setRecordsFiltered(total);
            json = pagination.parsePagination(false);
        }

        return json;
    }

    public SysMenuDao getSysMenuDao() {
        return sysMenuDao;
    }

    public void setSysMenuDao(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
    }

    public SysUserDao getSysUserDao() {
        return sysUserDao;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public SysUserRoleDao getSysUserRoleDao() {
        return sysUserRoleDao;
    }

    public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
        this.sysUserRoleDao = sysUserRoleDao;
    }

    public SysRoleDao getSysRoleDao() {
        return sysRoleDao;
    }

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    public SysRoleMenuDao getSysRoleMenuDao() {
        return sysRoleMenuDao;
    }

    public void setSysRoleMenuDao(SysRoleMenuDao sysRoleMenuDao) {
        this.sysRoleMenuDao = sysRoleMenuDao;
    }
}
