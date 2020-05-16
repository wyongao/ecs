package com.ecs.domain;

public class TrackTeacher {

	private Integer id;
	private String tnum;
	private String tname;
	private String school;
	private String college;
	private String addr;
	private String date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "TrackTeacher [id=" + id + ", tnum=" + tnum + ", tname=" + tname + ", school=" + school + ", college="
				+ college + ", addr=" + addr + ", date=" + date + "]";
	}
	
	public TrackTeacher() {}
	
	public TrackTeacher(Integer id, String tnum, String tname, String school, String college, String addr,
			String date) {
		super();
		this.id = id;
		this.tnum = tnum;
		this.tname = tname;
		this.school = school;
		this.college = college;
		this.addr = addr;
		this.date = date;
	}
	
	
}
