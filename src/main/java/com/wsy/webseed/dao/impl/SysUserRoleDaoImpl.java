package com.wsy.webseed.dao.impl;

import java.util.List;

import com.wsy.webseed.dao.BaseDao;
import com.wsy.webseed.dao.SysUserRoleDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wsy.webseed.dao.entity.SysUserRole;

@Repository("sysUserRoleDao")
public class SysUserRoleDaoImpl extends BaseDao implements SysUserRoleDao {

    /**
     * 将userRoles保存到SysUserRole
     * @param userRoles
     */
    @Override
    public void save(List<SysUserRole> userRoles) {
        saveList(userRoles);

    }

    /**
     * 根据roleId查询SysUserRole
     * @param id
     * @return
     */
    @Override
    public List<SysUserRole> queryByRoleId(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysUserRole.class);
        cri.add(Restrictions.eq("roleId", id));
        List<SysUserRole> list = cri.list();
        return list;
    }

    /**
     * 根据UserId删除SysUserRole
     * @param id
     */
    @Override
    public void deleteByUserId(int id) {
        String hql = "delete from SysUserRole s where s.userId = ?";
        getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();
        
    }

    /**
     * 根据UserId查询SysUserRole
     * @param id
     * @return
     */
    @Override
    public List<SysUserRole> queryByUserId(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysUserRole.class);
        cri.add(Restrictions.eq("userId", id));
        List<SysUserRole> list = cri.list();
        return list;
    }

}
