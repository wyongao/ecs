package com.ecs.domain;

public class AccessData {

	private Integer id;
	private String username;
	private String userid;
	private String college;
	private String ip;
	private String date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public AccessData() {
		super();

	}

	public AccessData(Integer id, String username, String userid, String college, String ip, String date) {
		super();
		this.id = id;
		this.username = username;
		this.userid = userid;
		this.college = college;
		this.ip = ip;
		this.date = date;
	}

	@Override
	public String toString() {
		return "AccessData [id=" + id + ", username=" + username + ", userid=" + userid + ", college=" + college
				+ ", ip=" + ip + ", date=" + date + "]";
	}

}
