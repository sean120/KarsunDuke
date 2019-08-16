package com.karsun.duke.utils;
import java.util.Base64;

public class Base64Util {

	public static String decodeString(String encodedString) {

		byte[] decoded = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decoded);
		return decodedString;
	}

	public static String encodeString(String inputString) {
		String encodedString = Base64.getEncoder().encodeToString(inputString.getBytes());
		return encodedString;
	}

}
