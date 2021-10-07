<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ajaxstudy.contact.domain.*" %>
<%@ page import="com.ajaxstudy.contact.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	String status = "ok";
	String message = "";
	
	//�Ķ���� ���� ����� POST �϶��� ó���ϰڴ�.
	if(request.getMethod().equals("POST")) {
		boolean isDel = false;
		long no = 0;
		
		try{
			//POST ������� ���۵� �Ķ���͸� ongŸ������ ����ȯ
			no = Long.parseLong(request.getParameter("no"));
			isDel = true;
		} catch(Exception e) {
			isDel = false;
			status = "fail";
			message = "��ȣ�� ���ڷ� ������ �� �����ϴ�.";
		}

		if(isDel) {
			SampleDAO.deleteContact(no);
			status = "ok";
			message = "�Ϸù�ȣ " + no +"�� �����Ͱ� �����Ǿ����ϴ�.";
		}
		
	//�Ķ���� ���� ����� POST�� �ƴ� ��
	} else {
		//���� �޽��� ǥ��
		status = "fail";
		message = "POST �޼��常 �����մϴ�.";
	}
%>
<!-- status, message ǥ�� -->
{
	"status" : "<%=status %>",
	"message" : "<%=message %>"
}