package com.wsy.webseed.dao.impl;

import java.util.List;

import com.wsy.webseed.dao.BaseDao;
import com.wsy.webseed.dao.SysUserDao;
import com.wsy.webseed.dao.entity.SysRole;
import com.wsy.webseed.dao.entity.SysUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    private Logger LOGGER = LoggerFactory.getLogger(SysUserDaoImpl.class);

    /**
     * 将sysUser对象保存到SysUser
     * @param sysUser
     * @return
     */
    @Override
    public int save(final SysUser sysUser) {
        saveObj(sysUser);
        int id = sysUser.getId();
        LOGGER.debug("saved user id : {}", id);
        return id;
    }

    /**
     * 查询SysUser所有信息
     * @return
     */
    @Override
    public List<SysUser> query() {
        Criteria cri = getCurrentSession().createCriteria(SysUser.class);
        cri.addOrder(Order.asc("id"));
        List<SysUser> list = cri.list();
        return list;
    }

    /**
     * 根据id查询SysUser
     * @param id
     * @return
     */
    @Override
    public SysUser query(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysUser.class);
        cri.add(Restrictions.eq("id", id));
        SysUser user = (SysUser) cri.uniqueResult();
        return user;
    }

    /**
     * 修改SysUser
     * @param user
     * @return
     */
    @Override
    public int edit(SysUser user) {
        updateObj(user);
        int id = user.getId();
        LOGGER.debug("edit user id : {}", id);
        return id;
    }

    /**
     * 根据id删除SysUser
     * @param id
     * @return
     */
    @Override
    public int del(int id) {
        String hql = "delete from SysUser s where s.id = ?";
        return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();
         
    }

    /**
     * 根据loginName查询SysUser
     * @param loginName
     * @return
     */
    @Override
    public SysUser query(String loginName) {
        Criteria cri = getCurrentSession().createCriteria(SysUser.class);
        cri.add(Restrictions.eq("loginName", loginName));
        SysUser user = (SysUser) cri.uniqueResult();
        return user;
    }

    /**
     * 根据loginName，id查询SysUser
     * @param loginName
     * @param id
     * @return
     */
    @Override
    public SysUser query(String loginName, int id) {
        SysUser user = query(loginName);
        if(user != null && user.getId() == id){
            return null;
        }
        return user;
    }

    /**
     * 根据userId查询SysRole
     * @param id
     * @return
     */
    @Override
    public List<SysRole> queryRolesByUserId(int id) {
        final StringBuffer sb = new StringBuffer();
        sb.append("select new SysRole(sr.id,sr.name,sr.isBuiltin,sr.updateTime,sr.createTime) from SysUserRole sur,SysRole sr where sur.roleId = sr.id and sur.userId = ?");
        final Query query = getCurrentSession().createQuery(sb.toString()).setInteger(0, id);
        final List<SysRole> list = query.list();

        return list;
    }

	@Override
	public Integer queryCount() {
        final Query query = getCurrentSession().createQuery("select count(id) from SysUser where 1 = 1");
        final int total = ((Number) query.uniqueResult()).intValue();
        return total;
	}


}
