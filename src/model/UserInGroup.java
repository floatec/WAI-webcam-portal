package model;

public class UserInGroup {

	String UserName;
	boolean inGroup = false;
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public boolean isInGroup() {
		return inGroup;
	}
	public void setInGroup(boolean inGroup) {
		this.inGroup = inGroup;
	}
	
}
