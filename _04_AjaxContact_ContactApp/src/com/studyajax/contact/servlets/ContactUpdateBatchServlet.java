package com.studyajax.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.domain.ContactList;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;
import com.ajaxstudy.contact.domain.Result;

/**
 * Servlet implementation class ContactAddServlet
 */
/*WebServlet : ���������� ȣ���� �ּ�*/
@WebServlet("/update_batch.do")
public class ContactUpdateBatchServlet extends HttpServlet {
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
		
		//JAVA ��ü -> json ��ü�� ��ȯ
		Result result = new Result(status, message);
		String json = Converter.convertToJson(result);
		
		//ȭ�鿡 ǥ��
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
}
