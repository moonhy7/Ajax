package com.ajaxstudy.contact.domain;

public class Result {
	private String status;
	private String message;
	

	// super()를 이용한 생성자
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 필드를 이용한 생성자
	public Result(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	//Getter and Setter 생성 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	// toString()
	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + "]";
	}
}
