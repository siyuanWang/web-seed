package com.wsy.webseed.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.wsy.webseed.dao.SysRoleDao;
import com.wsy.webseed.dao.SysUserRoleDao;
import com.wsy.webseed.dao.entity.SysRole;
import com.wsy.webseed.dao.entity.SysRoleMenu;
import com.wsy.webseed.dao.entity.SysUserRole;
import com.wsy.webseed.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wsy.webseed.dao.SysRoleMenuDao;
import com.wsy.webseed.dao.entity.SysMenu;

@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl implements SysRoleService {
    private Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    SysRoleDao roleDao;

    @Autowired SysRoleMenuDao roleMenuDao;

    @Autowired
    SysUserRoleDao userRoleDao;

    /**
     * 将role对象保存到SysRole
     * @param role
     */
    @Override
    public void save(SysRole role) {
        if (roleDao.query(role.getName()) == null) {
            int id = roleDao.save(role);
            LOGGER.debug("save role id:{}", id);
            // Set<SysRoleMenu> SysRoleMenus = role.getSysRoleMenus();
            // if(SysRoleMenus != null && !SysRoleMenus.isEmpty()){
            // roleMenuDao.save(SysRoleMenus);
            // }

        }
    }

    /**
     * 根据id查询SysRole
     * @param id
     * @return
     */
    @Override
    public SysRole query(int id) {
        SysRole role = roleDao.query(id);
        return role;
    }

    /**
     * 查询SysRole的所有信息
     * @return
     */
    @Override
    public List<SysRole> query() {
        List<SysRole> list = roleDao.query();
        return list;
    }

    /**
     * 修改SysRole
     * @param role
     * @param id
     */
    @Override
    public void edit(SysRole role, int id) {
        final Calendar cal = Calendar.getInstance();
        if (roleDao.query(role.getName(), id) == null) {
            role.setCreateTime(cal.getTime());
            role.setUpdateTime(cal.getTime());
            role.setIsBuiltin(false);
            int roleId = roleDao.edit(role);
            LOGGER.debug("edit role id:{}", roleId);
//            Set<SysRoleMenu> sysRoleMenus = role.getSysRoleMenus();
//            if (sysRoleMenus != null && !sysRoleMenus.isEmpty()) {
//                roleMenuDao.del(role.getId());
//                roleMenuDao.save(sysRoleMenus);
//            }
        }

    }

    /**
     * 根据id删除SysRole
     * @param id
     */
    @Override
    public void del(int id) {
        List<SysRoleMenu> sysRoleMenu = roleMenuDao.queryByRoleId(id);
        List<SysUserRole> list = userRoleDao.queryByRoleId(id);
        if (sysRoleMenu.size() > 0) {
            LOGGER.debug("del failed:RoleMenu");
        }
        if (list.size() > 0) {
            LOGGER.debug("del failed:userRole");
        }
        if (list.size() <= 0 && sysRoleMenu.size() <= 0) {
            roleDao.del(id);
            LOGGER.debug("del success:");
        }
    }

    /**
     * 根据roleId查询SysMenu
     * @param id
     * @return
     */
    @Override
    public List<SysMenu> queryRoleMenuByRoleId(int id) {
        List<SysMenu> list = roleDao.queryRoleMenusByRoleId(id);
        return list;
    }

    /**
     * 角色的菜单授权
     * @param roleId
     * @param menuIds
     */
    @Override
    public void assignRoleMenu(int roleId, Integer[] menuIds) {
        roleMenuDao.delByRoleId(roleId);
        saveRoleMenus(roleId, menuIds);

    }

    /**
     * 根据roleId，menuIds保存SysRoleMenu
     * @param roleId
     * @param menuIds
     */
    private void saveRoleMenus(int roleId, Integer[] menuIds) {
        final Calendar cal = Calendar.getInstance();
        List<SysRoleMenu> roleMenus = new ArrayList<SysRoleMenu>();
        SysRoleMenu roleMenu = new SysRoleMenu();
        for (Integer menuId : menuIds) {
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setCreateTime(cal.getTime());
            roleMenu.setUpdateTime(cal.getTime());
            roleMenus.add(roleMenu);
        }
        roleMenuDao.save(roleMenus);
    }

	public SysRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(SysRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public SysRoleMenuDao getRoleMenuDao() {
		return roleMenuDao;
	}

	public void setRoleMenuDao(SysRoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}

	public SysUserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(SysUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
}
