package com.ajaxstudy.contact.domain;

public class Result {
	private String status;
	private String message;
	

	// super()�� �̿��� ������
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	// �ʵ带 �̿��� ������
	public Result(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	//Getter and Setter ���� 
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
