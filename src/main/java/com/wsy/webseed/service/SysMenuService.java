package com.wsy.webseed.service;

import java.util.List;

import com.wsy.webseed.controller.pojo.tool.BootstrapTreeviewPojo;
import com.wsy.webseed.dao.entity.SysMenu;
import com.wsy.webseed.util.Pagination;

public interface SysMenuService {
	/**
	 * 查询当前用户权限下的树形菜单
	 * @param loginName 用户登陆名
	 * @return
	 */
	public List<SysMenu> query(String loginName);
	/**
	 * 查询所有的Boostrap-treeview数据类型的树形菜单
	 * @param loginName 用户登陆名
	 * @return
	 */
	public List<BootstrapTreeviewPojo> queryBootstrapTreeviewPojos(String loginName);
	/**
	 * 查询所有的非树形菜单
	 * @return
	 */
	public String queryNotTreeMenus(Pagination<SysMenu> pagination);
}
