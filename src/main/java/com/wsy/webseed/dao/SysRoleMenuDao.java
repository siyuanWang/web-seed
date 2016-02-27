package com.wsy.webseed.dao;

import java.util.List;

import com.wsy.webseed.dao.entity.SysRoleMenu;

public interface SysRoleMenuDao {

    //public void save(Set<SysRoleMenu> sysRoleMenus);

    /**
     * 通过roleId删除SysRoleMenu
     * @param id
     */
    public void delByRoleId(int id);

    /**
     * 将roleMenus集合保存到SysRoleMenu
     * @param roleMenus
     */
    public void save(List<SysRoleMenu> roleMenus);

    /**
     * 通过roleId查询SysRoleMenu
     * @param id
     * @return
     */
    public List<SysRoleMenu> queryByRoleId(int id);
    
    /**
     * 通过roleId的集合，查询SysRoleMenu
     * @param ids
     * @return
     */
    public List<SysRoleMenu> queryByRoleIds(List<Integer> ids);
}
