package com.onmobile.cryptography.aead;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class BrokenGCM1 {

	public static String[] plainTexts = { "I am not a cryptographer !!! I am not a mathematician!!! Then who i am ?",
			"I'm  a Simple Java Programmer!!!.. learining crypto!!!" };

	public static final int AES_KEY_SIZE = 256;
	// 12 * 8 = 96
	public static final int GCM_IV_LENGTH = 12;
	public static final int GCM_TAG_LENGTH = 16;

	public static void main(String[] args) throws Exception {

		// testBrokenGCM();

		substantiateBrokenGCM();
	}

	public static void testBrokenGCM() throws Exception {

		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		keygen.init(AES_KEY_SIZE);
		// key
		SecretKey key = keygen.generateKey();
		// IV
		byte[] IV = new byte[GCM_IV_LENGTH];

		SecureRandom random = new SecureRandom();
		random.nextBytes(IV);

		for (int i = 0; i < plainTexts.length; i++) {
			

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

	public static void substantiateBrokenGCM() {

		BigInteger plainText0 = new BigInteger(plainTexts[0].getBytes());
		// remove the last 32 hex chars as tag (16*8 = 128/4 = 32)
		BigInteger cipherText0 = new BigInteger(
				"6f2dcb7815c6b225278d2eac75e2eda131f5f8e7af85a1773c7b76c35b9e91574fa1f4a2d419e7eb6f5ead062e70f7b79a2b5161a34e6331565222b9a31561e8b463337dc057b29f",
				16);
		BigInteger cipherText1 = new BigInteger(
				"6f2ac73515c9fd026e817ea362bbd7b428f3aad6ad82a3777d373a8709f690170cafbaa1c558f4a26c56b7096b7ee4ba833c5721ec4e",
				16);

		BigInteger keyStream = plainText0.xor(cipherText0);

		// byte[] plainText1 = xor(toByteArray(keyStream), toByteArray(cipherText1));

		byte[] plainText1 = xor(keyStream.toByteArray(), cipherText1.toByteArray());

		System.out.println("decrypting ciphertext1 without key:" + new String(plainText1));
	}

	public static byte[] xor(byte[] arr1, byte[] arr2) {

		byte[] result = new byte[Math.min(arr1.length, arr2.length)];

		for (int i = 0; i < result.length; i++) {

			result[i] = (byte) (arr1[i] ^ arr2[i]);
		}

		return result;

	}

	public static byte[] toByteArray(BigInteger i) {
		byte[] iArr = i.toByteArray();
		if (iArr[0] == 0) {
			byte[] tmp = new byte[iArr.length - 1];
			System.arraycopy(iArr, 1, tmp, 0, tmp.length);
			iArr = tmp;
		}
		return iArr;
	}

}
