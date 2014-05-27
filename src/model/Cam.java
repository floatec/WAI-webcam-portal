package model;

public class Cam {
	
	private Long id;
	private String name;
	private String url;
	private boolean status;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			Cam other = (Cam) obj;
			return (other.id==this.id);
		} catch (Exception e) {
			return false;
		}

	}
}
