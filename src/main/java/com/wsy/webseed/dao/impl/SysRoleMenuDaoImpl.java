package com.wsy.webseed.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wsy.webseed.dao.BaseDao;
import com.wsy.webseed.dao.SysRoleMenuDao;
import com.wsy.webseed.dao.entity.SysRoleMenu;

@Repository("roleMenuDao")
public class SysRoleMenuDaoImpl extends BaseDao implements SysRoleMenuDao {

    /**
     * 通过roleId删除SysRoleMenu
     * @param id
     */
    @Override
    public void delByRoleId(int id) {
        String hql = "delete from SysRoleMenu s where s.roleId = ?";
        getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();
        
    }

    /**
     * 将roleMenus集合保存到SysRoleMenu
     * @param roleMenus
     */
    @Override
    public void save(List<SysRoleMenu> roleMenus) {
        saveList(roleMenus);
        
    }

    /**
     * 通过roleId查询SysRoleMenu
     * @param id
     * @return
     */
    @Override
    public List<SysRoleMenu> queryByRoleId(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysRoleMenu.class);
        cri.add(Restrictions.eq("roleId", id));
        List<SysRoleMenu> list = cri.list();
        return list;
    }

	@Override
	public List<SysRoleMenu> queryByRoleIds(List<Integer> ids) {
        Criteria cri = getCurrentSession().createCriteria(SysRoleMenu.class);
        cri.add(Restrictions.in("roleId", ids));
        List<SysRoleMenu> list = cri.list();
        return list;
	}

    
}
