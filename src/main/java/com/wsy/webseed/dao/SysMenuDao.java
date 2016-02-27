package com.wsy.webseed.dao;

import java.util.List;

import com.wsy.webseed.dao.entity.SysMenu;
import com.wsy.webseed.util.Pagination;

public interface SysMenuDao {
	public List<SysMenu> query(Pagination<SysMenu> pagination);
	
	public List<SysMenu> query(List<Integer> menuIds);
	
	public Integer queryCount();
	
	public Integer save(SysMenu menu);
}
