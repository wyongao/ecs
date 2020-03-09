package com.ecs.domain;
/**
 * 每日提交的学生的信息实体类
 * @author xuluyang
 *
 * 2020年3月8日
 */

public class DayStudent {
	private Integer id;
	private String snum;
	private String sname;
	private String college; //学院
	private String major; //专业
	private Integer classes; //班级
	private String addr; //地点
	private String date; //日期
	private Double temp; //体温
	private String symptom; //症状
	//无参数的构造方法
	public DayStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	//构造方法
	public DayStudent(String snum, String sname, String college, String major, Integer classes, String addr,
			String date, Double temp, String symptom) {
		super();
		this.snum = snum;
		this.sname = sname;
		this.college = college;
		this.major = major;
		this.classes = classes;
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

	public Integer getClasses() {
		return classes;
	}

	public void setClasses(Integer classes) {
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
		return "DayTeacher [id=" + id + ", snum=" + snum + ", sname=" + sname + ", college=" + college + ", major="
				+ major + ", classes=" + classes + ", addr=" + addr + ", date=" + date + ", temp=" + temp + ", symptom="
				+ symptom + "]";
	}

}