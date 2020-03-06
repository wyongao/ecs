package com.ecs.domain;
/**
 * class实体类
 * @author xuluyang
 *
 * 2020年3月5日
 */

public class Class {
	private Integer id;
	private String classname;
	private String parentid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	@Override
	public String toString() {
		return "Class [id=" + id + ", classname=" + classname + ", parentid=" + parentid + "]";
	}

}
