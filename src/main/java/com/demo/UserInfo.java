package com.demo;

/**
 * 用户信息.
 */
public class UserInfo {
	private String uid;
	private String name;
	private String city;
	private String nickName;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", name=" + name + ", city=" + city
				+ ", nickName=" + nickName + "]";
	}

}