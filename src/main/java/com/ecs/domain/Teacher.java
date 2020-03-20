package com.ecs.domain;

/**
 * teacher的实体类
 * 
 * @author xuluyang
 *
 *         2020年3月7日
 */
public class Teacher {
	private Integer id;
	private String tname;
	private String tnum;
	private String password;
	private String school;
	private String college;
	private String sex;
	private String tel;
	private String identify;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", tname=" + tname + ", tnum=" + tnum + ", password=" + password + ", school="
				+ school + ", college=" + college + ", sex=" + sex + ", tel=" + tel + ", identify=" + identify + "]";
	}
	
	public Teacher() {}
	
	public Teacher(Integer id, String tname, String tnum, String password, String school, String college, String sex,
			String tel, String identify) {
		super();
		this.id = id;
		this.tname = tname;
		this.tnum = tnum;
		this.password = password;
		this.school = school;
		this.college = college;
		this.sex = sex;
		this.tel = tel;
		this.identify = identify;
	}
	
	
	


	
}
