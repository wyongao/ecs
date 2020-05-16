package com.ecs.domain;

public class TrackStudent {
	
	private Integer id;
	private String snum;
	private String sname;
	private String school;//学校
	private String college; //学院
	private String major; //专业
	private String classes; //班级
	private String addr; //地点
	private String date; //日期
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSnum() {
		return snum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
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
		return "TrackStudent [id=" + id + ", snum=" + snum + ", sname=" + sname + ", school=" + school + ", college="
				+ college + ", major=" + major + ", classes=" + classes + ", addr=" + addr + ", date=" + date + "]";
	}
	
	public TrackStudent() {}
	
	public TrackStudent(Integer id, String snum, String sname, String school, String college, String major,
			String classes, String addr, String date) {
		super();
		this.id = id;
		this.snum = snum;
		this.sname = sname;
		this.school = school;
		this.college = college;
		this.major = major;
		this.classes = classes;
		this.addr = addr;
		this.date = date;
	}
	
	
	
}
