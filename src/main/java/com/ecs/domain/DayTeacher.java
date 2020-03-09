package com.ecs.domain;
/**
 * 每日提交的老师的信息实体类
 * @author xuluyang
 *
 * 2020年3月8日
 */

public class DayTeacher {
	private Integer id;
	private String tnum;
	private String tname;
	private String college;
	private String addr;
	private String date;
	private Double temp;
	private String symptom;
	
	public DayTeacher() {
		super();
	}
	public DayTeacher(Integer id, String tnum, String tname, String college, String addr, String date, Double temp,
			String symptom) {
		super();
		this.id = id;
		this.tnum = tnum;
		this.tname = tname;
		this.college = college;
		this.addr = addr;
		this.date = date;
		this.temp = temp;
		this.symptom = symptom;
	}
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
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	@Override
	public String toString() {
		return "DayTeacher [id=" + id + ", tnum=" + tnum + ", tname=" + tname + ", college=" + college + ", addr="
				+ addr + ", date=" + date + ", temp=" + temp + ", symptom=" + symptom + "]";
	}
	

}