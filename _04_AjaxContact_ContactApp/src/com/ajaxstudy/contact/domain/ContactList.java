package com.ajaxstudy.contact.domain;

import java.util.List;

public class ContactList { // 여러 건의 데이터를 한 번에 처리할 수 있도록 만듬
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private List<Contact> contacts;
	
	// 생성자
	public ContactList() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 생성자
	public ContactList(int pageNo, int pageSize, int totalCount, List<Contact> contacts) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.contacts = contacts;
	}
	
	// getter and setter
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	// toString()
	@Override
	public String toString() {
		return "ContactList [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", contacts="
				+ contacts + "]";
	}
	
	
}
