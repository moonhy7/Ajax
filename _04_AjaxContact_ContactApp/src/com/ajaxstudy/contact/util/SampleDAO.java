package com.ajaxstudy.contact.util;

import java.util.ArrayList;
import java.util.List;

import com.ajaxstudy.contact.domain.Contact;
import com.ajaxstudy.contact.domain.ContactList;

/*
����ó ����Ʈ�� �����ϰ� �ִ� Ŭ����
DB�� �������� �ʰ� �÷��� ��ü�� �ּҵ����� ����
����ó �߰�, ����, ����, ��ȸ ��� ����
*/
public class SampleDAO {
	//����ó ��Ͽ��� ����� �̸� ��� ���� ����
	private static String nameList = "���,����,�ǻ��,����,���,�����," +
									 "����,����,������,������,�ں���,�ڼ���," +
									 "������,������,������,������,���α�,������," +
									 "�۰�ȣ,�ű�,�����,������,���ÿ�,������," +
									 "����,������,������,�����,������,�̰���," +
									 "�̱���,�̴�ȭ,�̵���,�̺���,�̿���,���ر�," +
									 "�α���,��â��,��ä��,������,����ȯ,�����," +
									 "���켺,������,������,���μ�,������,������," +
									 "������,����,��â��,������,������,�ֿ�," +
									 "����ǥ,���¿�,�ִٴϿ�,�ֹμ�,�ֹν�,�ֺҾ�," +
									 "�ϼ���,����,ȫ��ȣ,Ȳ����";
	//����ó ����� ���� ���� ����
	private static List<Contact> contacts;
	//���� �Ϸù�ȣ�� ���� ���� ����
	private static long nextNo = 0;
	
	//������ �ʱ�ȭ(����ó ���� ��� ����)
	static {
		//�̸� ��� �迭 ����
		String[] names = nameList.split(",");
		//����ó ��� ���� ���� �ʱ�ȭ
		contacts = new ArrayList<Contact>();
		for(int i = 0; i < names.length; i++) {
			nextNo++; //�Ϸù�ȣ 1���� ����
			String tel = "010-1111-22" + (i+10);
			String address = "����Ư����";
			//����ó ���� ����
			Contact c = new Contact(nextNo, names[i], tel, address);
			//������ ������ ����ó ������ ����ó ��Ͽ� �ϳ��� ����
			//contacts.get(i)�� �ϳ��� ���� �� �ְ�
			//contacts.get(i)�� ������ {1, ���, 010-1111-2210, ����Ư����}
			contacts.add(i, c);
		}
	}
	
	//��ü ����ó ��� ��ȸ
	public static ContactList getContacts() {
		//static ��Ͽ��� ������ ����ó ������� �� ContactList ��ü ����
		//(���� ������ 0, ������ ������ 0, ��� ������ contacts.size(), List<Contact> contacts)
		ContactList cList = new ContactList(0, 0, contacts.size(), contacts);
		return cList;
	}
	
	//Ư�� �������� �������� ���Ե� ����ó ���� ��û(�Ϻ� ����ó ������ ����)
	public static ContactList getContacts(int pageno, int pagesize) {
		//������ ����ó ����� ������ġ ����
		int startIndex = (pageno-1) * pagesize;
		//������ ����ó ����� ����ġ ����
		int endIndex = startIndex + pagesize;
		
		List<Contact> temps = null;
		
		//����ó ����� ������ ��� ��û�� ����
		if(startIndex > contacts.size() -1 || startIndex < 0 || pagesize <1) {
			//�� ��ü�� ����
			temps = new ArrayList<Contact>();
		}
		//��ȿ�� ������ ��û�� ����
		else {
			//���� ���� ��ġ�� ����ó ����� ũ�⸦ �Ѿ��
			//����ġ�� ����ó ����� ������ �ε����� �����Ѵ�.
			if(endIndex > contacts.size()) {
				endIndex = contacts.size();
			}
			//������ġ�� ����ġ�� �ش��ϴ� ����ó ��� �κ��� ����
			temps = contacts.subList(startIndex, endIndex);
		}
		//������ ������ ����ó ����� �������� �� ����ó ��� ��ü ����
		//(���� ������ pageno, ������ ������ pagesize, ��� ������ contacts.size(), List<Contact> temps)
		ContactList cList = new ContactList(pageno, pagesize, contacts.size(), temps);
		
		return cList;
	}
	
	//����ó ��Ͽ� ����ó �߰�
	public static void addContact(Contact c) {
		nextNo++;
		c.setNo(nextNo);
		contacts.add(0, c);
	}
	
	//�ش� �Ϸù�ȣ�� ����ó ����
	public static void deleteContact(long no) {
		//������ ����ó�� ����
		int count = 0;
		for(int i = 0; i < contacts.size(); i++) {
			Contact c = contacts.get(i);
			//����ó�� �Ϸù�ȣ�� ���޹��� �Ϸù�ȣ�� ���� �� ��Ͽ��� ����
			if(c.getNo() == no) {
				contacts.remove(i);
				count++;
				break;
			}
		}
	}
	
	//����ó ����
	//������ ����ó ���� �Ű������� ����
	public static int updateContact(Contact c) {
		long no = c.getNo();
		//������ ����ó�� ����
		int count = 0;
		for(int i = 0; i < contacts.size(); i++) {
			//������ ����ó ��Ͽ��� ����ó �ϳ��� ������ ��
			Contact con = contacts.get(i);
			//�Ű������� ���� ����ó�� �Ϸù�ȣ�� ���� ����ó ��Ͽ��� ������ ����ó�� �Ϸù�ȣ�� ������
			if(con.getNo() == no) {
				//���� ����ó ������ �Ű������� ���� ����ó ������ �����Ѵ�.
				contacts.set(i, c);
				count++;
				break;
			}
		}
		//�ϳ��� �����Ǳ� ������ 1�� ����
		return count;
	}
	
	//�������� ����ó �� ���� ����
	//������ ����ó ��� �Ű������� ����
	public static int updateBatch(ContactList contactList) {
		//������ ����ó�� ����
		int count = 0;
		//�Ű��������� pageNo, pageSize, totalCount�� ������ ����ó ��ϸ� ����
		List<Contact> list = contactList.getContacts();
		if(list.size() > 0) {
			//����ó ��Ͽ��� ����ó ���� �ϳ��� ����
			for(Contact c : list) {
				//���� �޼ҵ� ȣ��
				SampleDAO.updateContact(c);
				count++;
			}
		}
		return count;
	}
	
	//���޹��� no �Ķ���ͷ� �ش� ������ ��ȸ �޼ҵ�
	public static Contact getContactByNo(long no) {
		Contact c = null;
		for(int i = 0; i < contacts.size(); i++) {
			Contact temp = contacts.get(i);
			if(temp.getNo() == no) {
				c = temp;
				break;
			}
		}
		
		return c;
	}
}
