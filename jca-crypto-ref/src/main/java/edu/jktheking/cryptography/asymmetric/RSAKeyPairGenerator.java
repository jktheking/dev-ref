package edu.jktheking.cryptography.asymmetric;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public final class RSAKeyPairGenerator {

	private PrivateKey privateKey;
	private PublicKey publicKey;

	public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
		KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance(RSA.KEY_AGREEMENT_ALGORITHM_NAME);
		rsaKeyGen.initialize(RSA.RSA_KEY_LENGTH);
		KeyPair rsaKeyPair = rsaKeyGen.generateKeyPair();
		this.privateKey = rsaKeyPair.getPrivate();
		this.publicKey = rsaKeyPair.getPublic();
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
		// keyPairGenerator.writeToFile("RSA/publicKey",
		// keyPairGenerator.getPublicKey().getEncoded());
		// keyPairGenerator.writeToFile("RSA/privateKey",
		// keyPairGenerator.getPrivateKey().getEncoded());

		System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
		System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));
	}
}
