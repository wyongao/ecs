package com.ecs.domain;

public class School {
	
	private Integer id; 
	private String schoolname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	@Override
	public String toString() {
		return "School [id=" + id + ", schoolname=" + schoolname + "]";
	}
	
}
