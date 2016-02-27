package com.wsy.webseed.dao;

import java.util.List;

import com.wsy.webseed.dao.entity.SysUserRole;

public interface SysUserRoleDao {

    /**
     * 将userRoles保存到SysUserRole
     * @param userRoles
     */
    public void save(List<SysUserRole> userRoles);

    /**
     * 根据roleId查询SysUserRole
     * @param id
     * @return
     */
    public List<SysUserRole> queryByRoleId(int id);

    /**
     * 根据UserId删除SysUserRole
     * @param id
     */
    public void deleteByUserId(int id);

    /**
     * 根据UserId查询SysUserRole
     * @param id
     * @return
     */
    public List<SysUserRole> queryByUserId(int id);
}
