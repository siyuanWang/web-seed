package com.wsy.webseed.dao.entity;

// Generated 2015-10-13 17:16:11 by Hibernate Tools 4.0.0

import javax.persistence.*;
import java.util.Date;

/**
 * SysUserRole generated by hbm2java
 */
@Entity
@Table(name = "sys_user_role")
public class SysUserRole implements java.io.Serializable {

	private int id;
	private int userId;
	private int roleId;
	private Date updateTime;
	private Date createTime;

	public SysUserRole() {
	}

	public SysUserRole(int id, int userId, int roleId, Date updateTime,
			Date createTime) {
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "role_id", nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 22)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 22)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
