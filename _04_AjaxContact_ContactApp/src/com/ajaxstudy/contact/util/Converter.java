package com.ajaxstudy.contact.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

public class Converter { // java�� json����, json�� java�� �������ִ� Ŭ����
	private static Gson gson;
	//static block gson ��ü �ʱ�ȭ
	static {
		gson = new Gson();
	}
	
	// JAVA ��ü -> Json ���ڿ��� ��ȯ
	public static String convertToJson(Object obj) {
		return gson.toJson(obj);
	}
	
	// Json ���ڿ� -> (�ش� Ŭ���� Ÿ���� )JAVA ��ü�� ��ȯ
	public static <T> T convertFromJson(String json, Class<T> type) {
		return gson.fromJson(json, type);
	}
	
	// �������� ����� ��Ʈ������ ���۵� Json ���ڿ� -> (�ش� Ŭ���� Ÿ���� )JAVA ��ü�� ��ȯ
	public static <T> T convertFromJsonStream(InputStream is, Class<T> type) {
		try {
			Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			return gson.fromJson(reader, type);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
