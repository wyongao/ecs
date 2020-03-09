package com.ecs.domain;
/**
 * 校内建筑的实体类
 * @author xuluyang
 *
 * 2020年3月7日
 */
public class Building {
	private	Integer id;
	private String buildingname;
	private Integer parentid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	@Override
	public String toString() {
		return "Building [id=" + id + ", buildingname=" + buildingname + ", parentid=" + parentid + "]";
	}
	
}
