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
		long no = 0;
		
		try{
			//POST ������� ���۵� �Ķ���͸� ongŸ������ ����ȯ
			no = Long.parseLong(request.getParameter("no"));
		} catch(Exception e) {
			System.out.println("��ȣ�� ���ڷ� ������ �� �����ϴ�.");
			return;
		}
		
		//request ��ü�κ��� �Ķ���� �� ����
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		
		//�̸��� ��ȭ��ȣ�� ���� ��
		if(name == null || name.equals("") || tel == null || tel.equals("")) {
			//���� �޽��� ǥ��
			status = "fail";
			message = "�̸��� ��ȭ��ȣ�� �ʼ� �Է� ���Դϴ�.";
		//�̸��� ��ȭ��ȣ�� ���������� ��������
		} else {
			//����ó ���� ��ü ����
			Contact c = new Contact(no, name, tel, address);
			//SampleDAO�� ���� �޼ҵ� ȣ��
			int count = SampleDAO.updateContact(c);
			if(count == 1) {
				status = "ok";
				message = "�Ϸù�ȣ " + c.getNo() + "�� �����Ͱ� �����Ǿ����ϴ�."; 
			} else {
				status = "fail";
				message = "�����Ϸ��� �����Ͱ� �������� �ʽ��ϴ�.";
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