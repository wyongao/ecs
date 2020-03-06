package com.ecs.domain;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */
public class College {
	private Integer id;
	private String collegename;
	private String parentid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Override
	public String toString() {
		return "College [id=" + id + ", collegename=" + collegename + ", parentid=" + parentid + "]";
	}

}
