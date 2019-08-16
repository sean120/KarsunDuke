package com.karsun.duke.utils;

public class TestBase64 {

	public static void main(String[] args) {
		String pwd="";
		System.out.println(pwd);
		String encodedPassword=encodePassword(pwd);
		System.out.println(encodedPassword);
		String decodedPassword=decodePassword(encodedPassword);
		System.out.println(decodedPassword);
	}

		private static String encodePassword(String pwd) {
		return Base64Util.encodeString(pwd).trim();
		}

		private static String decodePassword(String encPwd) {
		return Base64Util.decodeString(encPwd).trim();
		}
}
