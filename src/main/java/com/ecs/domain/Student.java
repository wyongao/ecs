package com.ecs.domain;
/**
 * 学生基本信息表
 * @author xuluyang
 *
 * 2020年3月7日
 */

public class Student {
	private Integer id;
	private String sname;
	private String snum;
	private String school;
	private String college;
	private String major;
	private String classes;
	private String sex;
	private String tel;
	private String openid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSnum() {
		return snum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", sname=" + sname + ", snum=" + snum + ", school=" + school + ", college="
				+ college + ", major=" + major + ", classes=" + classes + ", sex=" + sex + ", tel=" + tel + ", openid="
				+ openid + "]";
	}
	

}
