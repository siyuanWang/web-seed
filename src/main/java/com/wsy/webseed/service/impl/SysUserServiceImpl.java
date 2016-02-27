package com.wsy.webseed.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.wsy.webseed.dao.SysRoleDao;
import com.wsy.webseed.dao.SysUserRoleDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wsy.webseed.dao.SysUserDao;
import com.wsy.webseed.dao.entity.SysRole;
import com.wsy.webseed.dao.entity.SysUser;
import com.wsy.webseed.dao.entity.SysUserRole;
import com.wsy.webseed.service.SysUserService;
import com.wsy.webseed.util.CommonUtil;
import com.wsy.webseed.util.DataTableFormate;
import com.wsy.webseed.util.Pagination;
import com.wsy.webseed.util.SysConstant;

@Service("userService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private Logger         LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao     userDao;

    @Autowired
    private SysUserRoleDao userRoleDao;

    @Autowired
    private SysRoleDao roleDao;

    /**
     * 将sysUser保存到SysUser
     * 
     * @param user
     */
    @Override
    public void saveUser(SysUser user) {
        final Calendar cal = Calendar.getInstance();
        if (userDao.query(user.getLoginName()) == null) {
            setpasswordMD5(user.getPassword(), user);
            user.setCreateTime(cal.getTime());
            user.setLoginTime(cal.getTime());
            user.setPasswordLastModifyTime(cal.getTime());
            user.setUpdateTime(cal.getTime());
            user.setIsBuiltin(false);

            int id = userDao.save(user);
            LOGGER.debug("saved user id : {}", id);
        }
    }

    /**
     * 根据id查询SysUser
     * 
     * @param id
     * @return
     */
    @Override
    public SysUser query(int id) {
        SysUser user = userDao.query(id);
        LOGGER.debug("query user : {}", user);
        return user;
    }

    /**
     * 查询所有SysUser
     * 
     * @return
     */
    @Override
    public List<SysUser> query() {
        List<SysUser> list = userDao.query();
        return list;
    }

    /**
     * 修改SysUser
     * 
     * @param user
     * @param id
     */
    @Override
    public void edit(SysUser user, int id) {
        if (userDao.query(user.getLoginName(), id) == null) {
            final Calendar cal = Calendar.getInstance();
            user.setUpdateTime(cal.getTime());
            user.setPasswordLastModifyTime(cal.getTime());
            setpasswordMD5(user.getPassword(), user);
            userDao.edit(user);
        }
        LOGGER.debug("edit user id : {}", id);
    }

    /**
     * 根据id删除SysUser
     * 
     * @param id
     */
    @Override
    public void del(int id) {
        List<SysUserRole> userRoleList = userRoleDao.queryByUserId(id);
        if (userRoleList.size() > 0) {
            userRoleDao.deleteByUserId(id);
        }
        int del = userDao.del(id);
        LOGGER.debug("del user del : {}", del);

    }

    /**
     * 设置加密密码
     * 
     * @param password
     * @param user
     */
    private void setpasswordMD5(String password, SysUser user) {
        if (StringUtils.isNotEmpty(password)) {
            user.setPassword(CommonUtil.string2MD5(password));
        }
    }

    /**
     * 用户的角色授权
     * 
     * @param userId
     * @param roleIds
     */
    @Override
    public void updateUserRole(int userId, Integer[] roleIds) {
        userRoleDao.deleteByUserId(userId);
        insertUserRole(userId, roleIds);

    }

    /**
     * 根据userId查询SysRole
     * 
     * @param id
     * @return
     */
    @Override
    public List<SysRole> queryRolesByUserId(int id) {
        List<SysRole> list = userDao.queryRolesByUserId(id);
        return list;
    }

    /**
     * 根据userId，roleIds添加信息到SysUserRole
     * 
     * @param userId
     * @param roleIds
     */
    private void insertUserRole(int userId, Integer[] roleIds) {
        final Calendar cal = Calendar.getInstance();
        List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
        SysUserRole bean = new SysUserRole();
        for (Integer roleId : roleIds) {
            bean.setUserId(userId);
            bean.setRoleId(roleId);
            bean.setCreateTime(cal.getTime());
            bean.setUpdateTime(cal.getTime());
            userRoles.add(bean);
        }
        userRoleDao.save(userRoles);

    }

    /**
     * 根据loginName查询SysUser
     * 
     * @param loginName
     * @return
     */
    @Override
    public SysUser query(String loginName) {
        SysUser user = userDao.query(loginName);
        return user;
    }

    /**
     * 根据loginId和password修改SysUser密码
     * 
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public int queryEditPwd(String loginName, String password) {
        final Calendar cal = Calendar.getInstance();
        SysUser user = query(loginName);
        if (user != null) {
            if (StringUtils.isNotBlank(password)) {
                user.setPassword(CommonUtil.string2MD5(password));
                user.setPasswordLastModifyTime(cal.getTime());
            }
            userDao.edit(user);
            return SysConstant.SUCCESS;
        } else {
            return SysConstant.FAILED;
        }

    }

    @Override
    public String queryTableJson(Pagination<SysUser> pagination) {
        List<SysUser> list = query();
        if (pagination == null) {
            return DataTableFormate.formateTableJsonStrNoPagination(list, list.get(0).getSimplePropertyPreFilter());
        } else {
            pagination.setData(list);
            int total = userDao.queryCount();
            pagination.setRecordsTotal(total);
            pagination.setRecordsFiltered(total);
            return pagination.parsePagination(true);
        }
    }

    public void setUserDao(SysUserDao userDao) {
        this.userDao = userDao;
    }

    public SysUserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public void setUserRoleDao(SysUserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public SysRoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(SysRoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
