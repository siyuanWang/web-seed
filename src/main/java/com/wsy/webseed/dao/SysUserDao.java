package com.wsy.webseed.dao;

import java.util.List;

import com.wsy.webseed.dao.entity.SysRole;
import com.wsy.webseed.dao.entity.SysUser;

public interface SysUserDao {

    /**
     * 查询SysUser所有信息
     * @return
     */
    public List<SysUser> query();

    /**
     * 根据id查询SysUser
     * @param id
     * @return
     */
    public SysUser query(int id);
    
    /**
     * 根据loginName查询SysUser
     * @param loginName
     * @return
     */
    public SysUser query(String loginName);
    
    /**
     * 根据loginName，id查询SysUser
     * @param loginName
     * @param id
     * @return
     */
    public SysUser query(String loginName,int id);
    
    /**
     * 将sysUser对象保存到SysUser
     * @param sysUser
     * @return
     */
    public int save(SysUser sysUser);
    
    /**
     * 修改SysUser
     * @param user
     * @return
     */
    public int edit(SysUser user);
    
    /**
     * 根据id删除SysUser
     * @param id
     * @return
     */
    public int del(int id);

    /**
     * 根据userId查询SysRole
     * @param id
     * @return
     */
    public List<SysRole> queryRolesByUserId(int id);
    /**
     * 查询总记录数
     * @return
     */
    public Integer queryCount();
}
