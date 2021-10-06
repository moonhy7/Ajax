package com.studyajax.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.ContactList;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;

/**
 * Servlet implementation class ContactAddServlet
 */
/*list.do : servlet*/
@WebServlet("/list_long.do")
public class ContactListLongServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//doGet : Get����� ��� �̷����� �� ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ڰ� ���� �Ķ���� ����
		//���Ƿ� ������ ������ �༭ 2�� �Ŀ� �����ϵ��� ����
		try {
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=UTF-8");

		String strPageNo = request.getParameter("pageno");
		String strPageSize = request.getParameter("pagesize");
		
		int pageno = 0; //0�̸� ��ü ������ ��ȸ, 1�̻��̸� �ش� ������ ��ȸ
		int pagesize = 3; //�� �������� ũ��
		
		//�������� ������ �������� ��ȣ�� ������ ũ�⸦ int�� ��ȯ
		try {
			pageno = Integer.parseInt(strPageNo);
		} catch(Exception e) {
			pageno = 0;
			System.out.println("pageno �� ����");
		}
		
		try {
			pagesize = Integer.parseInt(strPageSize);
		} catch(Exception e) {
			pagesize = 3;
			System.out.println("pagesize �� ����");
		}
		ContactList contactList = null;
		if(pageno == 0) {
			//pageno�� 0�̸� ��ü ����ó ��� ��ȸ
			contactList = SampleDAO.getContacts();
		} else {
			//pageno�� 1 �̻��̸� Ư�� �������� ����ó ��� ��ȸ
			contactList = SampleDAO.getContacts(pageno, pagesize);
		}
		//JAVA ��ü -> Json ���ڿ��� ����
		String json = Converter.convertToJson(contactList);
		
		//���⸸ �߰���
		PrintWriter writer = response.getWriter(); 
		writer.println(json);
	}

}
