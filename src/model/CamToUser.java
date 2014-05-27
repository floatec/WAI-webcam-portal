package model;

public class CamToUser {
	private Long access;
	private String name;
	private Long camid;
	
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

	public Long getCamid() {
		return camid;
	}

	public void setCamid(Long camid) {
		this.camid = camid;
	}
}
