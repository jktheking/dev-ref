package com.onmobile.cryptography.aead;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class BrokenGCMBirthdayParadox2 {

	public static String[] plainTexts = {

			"4746 5342 3391 2418", "4746 5342 3391 2423", "4746 5342 3391 2419", "4746 5342 3391 1000",
			"4746 5000 3391 2418", "4746 1000 3391 2418", "4746 2000 3391 2418", "4746 5342 2000 2418",
			"4746 5342 9000 2418", "4746 5342 3391 1200", "4746 5342 3391 13000", "4746 5342 3391 13400",
			"1000 5342 3391 12345", "4746 9000 3391 12345", "4746 5342 5342 12345", "4746 3391 3391 12345",
			"4746 1200 8790 12345", "00000 1200 8790 12345", "4721 1200 8790 12345", "10000 1200 8790 12345"

	};

	public static final int AES_KEY_SIZE = 256;

	public static final int GCM_IV_LENGTH = 12; // 12*8 = 96 ; 2^96 ; birthday collision starts at 2^48
	public static final int GCM_TAG_LENGTH = 16;

	public static void main(String[] args) throws Exception {
		// testBrokenGCM();

		testBirthdayCollision();
	}

	public static void testBirthdayCollision() {
		IntStream.range(0, 10).forEach(i -> {
			System.out.println("executing " + i + "th instance of getBirthdayCollision");
			getBirthdayCollision();
		});
	}

	public static void getBirthdayCollision() {

		SecureRandom random = new SecureRandom();

		Set<String> hexIVSet = new HashSet<>();
		for (int i = 0; i <16; i++) {

			// 1bytes i.e. capable of generating 2^8 unique IV's; but because of
			// birthday-paradox would start IV repetition at just 2^4.
			// IV
			byte[] IV = new byte[1];

			random.nextBytes(IV);

			String hexIv = Hex.encodeHexString(IV);
			if (hexIVSet.contains(hexIv)) {
				System.out.println("birthday collision at i=" + i);
				break;
			}

			hexIVSet.add(hexIv);

		}
		//System.out.println(hexIVSet);

	}

	public static void testGCM() throws Exception {

		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(AES_KEY_SIZE);
		// key
		SecretKey key = keygen.generateKey();

		SecureRandom random = new SecureRandom();

		for (int i = 0; i < plainTexts.length; i++) {

			// IV
			byte[] IV = new byte[GCM_IV_LENGTH];

			random.nextBytes(IV);

			byte[] cipherText = encrypt(plainTexts[i].getBytes(), key, IV);

			System.out.println(Hex.encodeHex(IV));
			System.out.println(Hex.encodeHex(cipherText));

			String decryptedText = decrypt(cipherText, key, IV);
			System.out.println(decryptedText);

		}

	}

	public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		SecretKeySpec keySepc = new SecretKeySpec(key.getEncoded(), "AES");

		GCMParameterSpec gcmParamSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

		cipher.init(Cipher.ENCRYPT_MODE, keySepc, gcmParamSpec);

		return cipher.doFinal(plaintext);

	}

	public static String decrypt(byte[] ciphertext, SecretKey key, byte[] IV) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		SecretKeySpec keySepc = new SecretKeySpec(key.getEncoded(), "AES");

		GCMParameterSpec gcmParamSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

		cipher.init(Cipher.DECRYPT_MODE, keySepc, gcmParamSpec);

		return new String(cipher.doFinal(ciphertext));

	}

}
