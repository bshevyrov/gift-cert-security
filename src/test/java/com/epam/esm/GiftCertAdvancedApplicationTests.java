package com.epam.esm;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

class GiftCertAdvancedApplicationTests {

	@Test
	void contextLoads() {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		System.out.println(display(key.getEncoded()));


	}

	public static String display(byte[] b1) {
		StringBuilder strBuilder = new StringBuilder();
		for (byte val : b1) {
			strBuilder.append(String.format("%02x", val & 0xff));
		}
		return strBuilder.toString();

	}
}