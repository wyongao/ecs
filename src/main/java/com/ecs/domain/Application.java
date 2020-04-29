package com.ecs.domain;
/**
 * 出入校申请的实体类
 * @author xuluyang
 *
 * 2020年3月9日
 */

public class Application {
	private Integer id;
	private String snum;
	private String sname;
	private String date;
	private String exit;
	private String dest;
	private String reason;
	private String inout;
	private String status;
	private String school;
	private String college;
	private String major;
	private String classes;
	
	
	public Application() {
		super();
		
	}
	
	public Application(String snum, String sname, String date, String exit, String dest, String reason,
			String inout, String status, String school, String college, String major, String classes) {
		super();
		this.snum = snum;
		this.sname = sname;
		this.date = date;
		this.exit = exit;
		this.dest = dest;
		this.reason = reason;
		this.inout = inout;
		this.status = status;
		this.school = school;
		this.college = college;
		this.major = major;
		this.classes = classes;
	}

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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExit() {
		return exit;
	}
	public void setExit(String exit) {
		this.exit = exit;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getInout() {
		return inout;
	}
	public void setInout(String inout) {
		this.inout = inout;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Application [id=" + id + ", snum=" + snum + ", sname=" + sname + ", date=" + date + ", exit=" + exit
				+ ", dest=" + dest + ", reason=" + reason + ", inout=" + inout + ", status=" + status + ", school="
				+ school + ", college=" + college + ", major=" + major + ", classes=" + classes + "]";
	}
	


}
