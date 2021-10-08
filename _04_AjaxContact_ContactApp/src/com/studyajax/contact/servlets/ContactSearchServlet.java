package com.studyajax.contact.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.domain.ErrorMessage;
import com.ajaxstudy.contact.util.Converter;
import com.ajaxstudy.contact.util.SampleDAO;
/**
 * Servlet implementation class ContactAddServlet
 */
//list.do : servlet
@WebServlet("/search.do")
public class ContactSearchServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		boolean isNum = true;
		//ajax ȣ�� �� �����ִ� �Ķ���� ���� ����
		String strNo = request.getParameter("no");
		long no = -1L;
		
		try {
			no = Long.parseLong(strNo);
		} catch(Exception e) {
			isNum = false;
		}
		
		String json = "";
		
		if(isNum) {
			//���޹��� no ���� �ش� ������ ��ȸ�ϴ� �޼ҵ�
			Contact c = SampleDAO.getContactByNo(no);
			if(c != null) {
				json = Converter.convertToJson(c);
			} else {
				ErrorMessage errorMessage = new ErrorMessage("�ش� �����Ͱ� �����ϴ�.");
				json = Converter.convertToJson(errorMessage);
			}
		} else {
			ErrorMessage errorMessage = new ErrorMessage("��ȣ�� ���ڷ� ��ȯ�� �� �����ϴ�.");
			json = Converter.convertToJson(errorMessage);
		}
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
}
