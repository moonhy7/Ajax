package com.studyajax.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;
import com.ajaxstudy.contact.domain.Result;

/**
 * Servlet implementation class ContactAddServlet
 */
/*WebServlet : ���������� ȣ���� �ּ�*/
@WebServlet("/update.do")
public class ContactUpdateServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//doGet : Get����� ��� �̷����� �� ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get ������� �������� status�� message����
		Result result = new Result("fail", "POST�޼ҵ常 �����մϴ�.");
		//result ��ü�� json ���ڿ��� ��ȯ
		String json = Converter.convertToJson(result);
		//��ȯ�� json ���ڿ� ȭ�鿡 ǥ��
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//doPost : Post����� ��� �̷����� �� ����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Content-Type�� html�� ����
		response.setContentType("text/html;charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		String status = "ok";
		String message = "";
		
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
		
		//JAVA ��ü -> json ��ü�� ��ȯ
		Result result = new Result(status, message);
		String json = Converter.convertToJson(result);
		
		//ȭ�鿡 ǥ��
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
}
