package com.wsy.webseed.dao.impl;

import java.util.List;

import com.wsy.webseed.dao.BaseDao;
import com.wsy.webseed.dao.SysMenuDao;
import com.wsy.webseed.dao.entity.SysMenu;
import com.wsy.webseed.util.Pagination;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("sysMenuDao")
public class SysMenuDaoImpl extends BaseDao implements SysMenuDao {

	@Override
	public List<SysMenu> query(Pagination<SysMenu> pagination) {
		Criteria cri = getCurrentSession().createCriteria(SysMenu.class);
		cri.addOrder(Order.asc("pid")).addOrder(Order.asc("id"));
		if(pagination != null) {
			cri.setFirstResult(pagination.getStart()); 
			cri.setMaxResults(pagination.getLength());
		}
		@SuppressWarnings("unchecked")
		List<SysMenu> list = cri.list();
		return list;
	}

	@Override
	public List<SysMenu> query(List<Integer> menuIds) {
		Criteria cri = getCurrentSession().createCriteria(SysMenu.class);
		cri.add(Restrictions.in("id", menuIds));
		cri.addOrder(Order.asc("pid")).addOrder(Order.asc("orderNum"));
		@SuppressWarnings("unchecked")
		List<SysMenu> list = cri.list();
		return list;
	}

	@Override
	public Integer queryCount() {
        final Query query = getCurrentSession().createQuery("select count(id) from SysMenu where 1 = 1");
        final int total = ((Number) query.uniqueResult()).intValue();
        return total;
	}

	@Override
	public Integer save(SysMenu menu) {
		saveObj(menu);
		int id = menu.getId();
		return id;
	}

	
}
