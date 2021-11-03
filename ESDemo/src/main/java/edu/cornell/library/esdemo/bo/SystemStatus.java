package edu.cornell.library.esdemo.bo;

public class SystemStatus {
	
	private String name;
	private String status;
	private String detail;

	public SystemStatus() {
		// TODO Auto-generated constructor stub
	}
	
	public SystemStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
