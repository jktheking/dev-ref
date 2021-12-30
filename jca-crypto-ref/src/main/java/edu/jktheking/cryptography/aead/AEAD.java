package com.onmobile.cryptography.aead;

import java.security.GeneralSecurityException;
import java.util.Base64;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;

public class AEAD {

	public static void main(String[] args) throws GeneralSecurityException {
		
		AeadConfig.register();
		 // 1. Generate the key material.
	    KeysetHandle keysetHandle = KeysetHandle.generateNew(
	        KeyTemplates.get("AES128_GCM"));

	    // 2. Get the primitive.
	    Aead aead = keysetHandle.getPrimitive(Aead.class);

	    // 3. Use the primitive to encrypt a plaintext,
	    byte[] ciphertext1 = aead.encrypt("jitendra".getBytes(), null);
	    byte[] ciphertext2 = aead.encrypt("jitendra".getBytes(), null);
	    
	    System.out.println(Base64.getEncoder().encodeToString(ciphertext1));
	    System.out.println(Base64.getEncoder().encodeToString(ciphertext2));
	    

	    // ... or to decrypt a ciphertext.
	    byte[] decrypted1 = aead.decrypt(ciphertext1, null);
	    byte[] decrypted2 = aead.decrypt(ciphertext2, null);
	    
	    System.out.println("decrypted:"+new String(decrypted1));
	    System.out.println("decrypted:"+new String(decrypted2));
	}
}
