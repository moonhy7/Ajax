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
		//�Ķ���͸� Json ������ ������ ����Ʈ�� InputStream���� �о JAVA��ü�� ��ȯ
		ContactList contactList = 
			Converter.convertFromJsonStream(request.getInputStream(), ContactList.class);
		if(contactList == null) {
			status = "fail";
			message = "��û ���� json ������ ��ü ��ȯ ����";
		} else {
			int count = SampleDAO.updateBatch(contactList);
			if(count > 0) {
				status = "ok";
				message = "�� " + count + "���� �����Ͱ� �����Ǿ����ϴ�."; 
			} else {
				status = "fail";
				message = "������ �����Ͱ� �������� �ʽ��ϴ�.";
			}
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