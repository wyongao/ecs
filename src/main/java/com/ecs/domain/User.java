package com.ecs.domain;

public class User {
	private Integer id;
	private String name;
	private String passWord;
	private String identify;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", passWord=" + passWord + ", identify=" + identify + "]";
	}

	
}
