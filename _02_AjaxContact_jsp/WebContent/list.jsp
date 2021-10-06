<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- java class import -->
<%@ page import="java.util.*" %>
<%@ page import="com.ajaxstudy.contact.domain.*" %>
<%@ page import="com.ajaxstudy.contact.util.*" %>

<!-- java 소스 구현 -->
<%
	//사용자가 보낸 파라미터 받음
	String strPageNo = request.getParameter("pageno");
	String strPageSize = request.getParameter("pagesize");
	
	int pageno = 0; //0이면 전체 데이터 조회, 1이상이면 해당 페이지 조회
	int pagesize = 3; //한 페이지의 크기
	
	//브라우저가 보내온 페이지의 번호와 페이지 크기를 int로 변환
	try {
		pageno = Integer.parseInt(strPageNo);
	} catch(Exception e) {
		pageno = 0;
		System.out.println("pageno 가 없음");
	}
	
	try {
		pagesize = Integer.parseInt(strPageSize);
	} catch(Exception e) {
		pagesize = 3;
		System.out.println("pagesize 가 없음");
	}
	ContactList contactList = null;
	if(pageno == 0) {
		//pageno이 0이면 전체 연락처 목록 조회
		contactList = SampleDAO.getContacts();
	} else {
		//pageno이 1 이상이면 특정 페이지의 연락처 목록 조회
		contactList = SampleDAO.getContacts(pageno, pagesize);
	}
	//JAVA 객체 -> Json 문자열로 변한
	String json = Converter.convertToJson(contactList);
%>
<!-- Json 문자열 표출 -->
<%=json%>