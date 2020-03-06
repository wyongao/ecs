package com.ecs.domain;
/**
 * 专业的实体类
 * @author xuluyang
 *
 * 2020年3月5日
 */
public class Major {
	private Integer id;
	private String majorname;
	private String parentid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	@Override
	public String toString() {
		return "Major [id=" + id + ", majorname=" + majorname + ", parentid=" + parentid + "]";
	}
}
