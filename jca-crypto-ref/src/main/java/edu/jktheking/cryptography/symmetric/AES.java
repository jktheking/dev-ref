package edu.jktheking.cryptography.symmetric;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AES {

	private AES() {

	}

	private static final Logger logger = LoggerFactory.getLogger(AES.class);

	private static final int AES_KEY_SIZE_BITS = 256;
	private static final int INITIALIZATION_VECTOR_SIZE_BITS = 128;
	private static final String SYMMETRIC_KEY_CRYPTO_ALGO = "AES";
	private static final String ALGO_TRANSFORMATION_STRING = "AES/CBC/PKCS5Padding";
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	/**
	 * Returns a base64 encoded 256 bit AES key and an initialization vector which
	 * will be used for encryption in CBC mode for an established session between
	 * the client and server. This would need JCE Unlimited strength files to be
	 * installed explicitly to the JVM. If they are not installed this method will
	 * throw an exception.
	 */

	public static AESContext generateAESContext() {
		SecretKey aesKey = null;
		try {

			KeyGenerator keygen = KeyGenerator.getInstance(SYMMETRIC_KEY_CRYPTO_ALGO);
			/**
			 * Specifying Key size to be used, Note: This would need JCE Unlimited Strength
			 * to be installed explicitly
			 */
			keygen.init(AES_KEY_SIZE_BITS);
			aesKey = keygen.generateKey();

			SecureRandom secureRandom = new SecureRandom();
			byte[] initializationVector = new byte[INITIALIZATION_VECTOR_SIZE_BITS / 8];
			secureRandom.nextBytes(initializationVector);

			return new AESContext.AESContextBuilder().aesKey(aesKey.getEncoded())
					.base64EncodedAesKey(Base64.getEncoder().encodeToString(aesKey.getEncoded()))
					.aesKeySizeInBytes(AES_KEY_SIZE_BITS / 8).initializationVector(initializationVector)
					.base64EncodedIV(Base64.getEncoder().encodeToString(initializationVector)).build();

		} catch (NoSuchAlgorithmException nsae) {
			logger.error(
					"NoSuchAlgorithmException occurred. Key being request is for AES algorithm, but this cryptographic "
							+ "algorithm is not available in the environment.");
			throw new SymmetricCryptographyException(
					"NoSuchAlgorithmException occurred. Key being request is for AES algorithm, but this cryptographic"
							+ " algorithm is not available in the environment.",
					nsae);
		} catch (InvalidParameterException ipe) {
			logger.error(
					"InvalidParameterException occurred. Key being request is for AES algorithm, but 256 bit key cannot be generated. "
							+ "Please install the JCE Unlimited Strength files.");
			throw new SymmetricCryptographyException(
					"InvalidParameterException occurred. Key being request is for AES algorithm, but 256 bit key cannot be generated. "
							+ "Please install the JCE Unlimited Strength files.",
					ipe);
		}
	}

	public static String encrypt(String clearTextMessage, byte[] aesKey, byte[] initializationVector) {

		Cipher c = null;
		IvParameterSpec spec = new IvParameterSpec(initializationVector);

		try {
			c = Cipher.getInstance(ALGO_TRANSFORMATION_STRING); // Transformation specifies algortihm, mode of operation
																// and padding
		} catch (NoSuchAlgorithmException nsae) {
			logger.error(
					"NoSuchAlgorithmException while encrypting. Algorithm being requested is not available in this environment.");
			throw new SymmetricCryptographyException(
					"NoSuchAlgorithmException while encrypting. Algorithm being requested is not available in this environment.",
					nsae);
		} catch (NoSuchPaddingException nspe) {
			logger.error(
					"NoSuchPaddingException while encrypting. Padding Scheme being requested is not available this environment.");
			throw new SymmetricCryptographyException(
					"NoSuchPaddingException while encrypting. Padding Scheme being requested is not available this environment.",
					nspe);
		}

		try {
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(aesKey, SYMMETRIC_KEY_CRYPTO_ALGO), spec);
		} catch (InvalidKeyException ike) {
			logger.error(
					"InvalidKeyException while encrypting. Key being used is not valid. It could be due to invalid encoding, wrong length or uninitialized.");
			throw new SymmetricCryptographyException(
					"InvalidKeyException while encrypting. Key being used is not valid. It could be due to invalid encoding, wrong length or uninitialized.",
					ike);
		} catch (InvalidAlgorithmParameterException iape) {
			logger.error(
					"InvalidAlgorithmParameterException while encrypting. Parameters passed to algorithm initialization are invalid.");
			throw new SymmetricCryptographyException(
					"InvalidAlgorithmParameterException while encrypting. Parameters passed to algorithm initialization are invalid.",
					iape);
		}

		byte[] cipherTextInByteArr = null;
		try {
			cipherTextInByteArr = c.doFinal(clearTextMessage.getBytes(CHARACTER_ENCODING));
		} catch (IllegalBlockSizeException ibse) {
			logger.error("IllegalBlockSizeException while encrypting, due to invalid block size.");
			throw new SymmetricCryptographyException(
					"IllegalBlockSizeException while encrypting, due to invalid block size.", ibse);
		} catch (BadPaddingException bpe) {
			logger.error("BadPaddingException while encrypting, due to invalid padding scheme.");
			throw new SymmetricCryptographyException(
					"BadPaddingException while encrypting, due to invalid padding scheme.", bpe);
		}
		return Base64.getEncoder().encodeToString(cipherTextInByteArr);

	}

	public static String decrypt(String base64EncodedEncryptedMessage, byte[] aesKey, byte[] initializationVector) {
		Cipher c = null;
		IvParameterSpec spec = new IvParameterSpec(initializationVector);

		try {
			c = Cipher.getInstance(ALGO_TRANSFORMATION_STRING);
		} catch (NoSuchAlgorithmException nsae) {
			logger.error(
					"NoSuchAlgorithmException while decrypting. Algorithm being requested is not available in environment.");
			throw new SymmetricCryptographyException(
					"NoSuchAlgorithmException while decrypting. Algorithm being requested is not available in environment ",
					nsae);
		} catch (NoSuchPaddingException nspe) {
			logger.error(
					"NoSuchPaddingException while decrypting. Padding scheme being requested is not available in environment.");
			throw new SymmetricCryptographyException(
					"NoSuchPaddingException while decrypting. Padding scheme being requested is not available in environment.",
					nspe);
		}

		try {
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(aesKey, SYMMETRIC_KEY_CRYPTO_ALGO), spec);
		} catch (InvalidKeyException ike) {
			logger.error(
					"InvalidKeyException while decrypting. Key being used is not valid. It could be due to invalid encoding, wrong length or uninitialized.");
			throw new SymmetricCryptographyException(
					"InvalidKeyException while decrypting. Key being used is not valid. It could be due to invalid encoding, wrong length or uninitialized.",
					ike);
		} catch (InvalidAlgorithmParameterException iape) {
			logger.error("InvalidAlgorithmParameterException while decrypting. Iv Parameter spec is not valid.");
			throw new SymmetricCryptographyException(
					"InvalidAlgorithmParameterException while decrypting. Iv Parameter spec is not valid.", iape);
		}

		byte[] plainTextInByteArr = null;
		try {
			plainTextInByteArr = c.doFinal(Base64.getDecoder().decode(base64EncodedEncryptedMessage));
		} catch (IllegalBlockSizeException ibse) {
			logger.error("IllegalBlockSizeException while decryption, due to block size.");
			throw new SymmetricCryptographyException("IllegalBlockSizeException while decryption, due to block size.",
					ibse);
		} catch (BadPaddingException bpe) {
			logger.error("BadPaddingException while decryption, due to padding scheme.");
			throw new SymmetricCryptographyException("BadPaddingException while decryption, due to padding scheme.",
					bpe);
		}
		return new String(plainTextInByteArr, CHARACTER_ENCODING);
	}

	public static String encrypt(String clearTextMessage, String aesKey, String base64EncodedIV) {
		return encrypt(clearTextMessage, Base64.getDecoder().decode(aesKey),
				Base64.getDecoder().decode(base64EncodedIV));
	}

	public static String decrypt(String base64EncodedEncryptedMessage, String aesKey, String base64EncodedIV) {
		return decrypt(base64EncodedEncryptedMessage, Base64.getDecoder().decode(aesKey),
				Base64.getDecoder().decode(base64EncodedIV));
	}

}
