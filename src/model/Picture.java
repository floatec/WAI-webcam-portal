package model;

public class Picture {
	
	private long id;
	private String path;
	private String timestamp;
	private long camId;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public long getCamId() {
		return camId;
	}
	
	public void setCamId(long cam_id) {
		this.camId = cam_id;
	}
}
