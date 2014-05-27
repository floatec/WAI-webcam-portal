package model;

public class UserInGroup {

	private Long access;
	private String name;
	private Long userid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAccess() {
		return access;
	}
	public void setAccess(Long access) {
		this.access = access;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
}
