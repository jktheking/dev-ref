package com.onmobile.cryptography.aead;

import java.security.GeneralSecurityException;
import java.util.Base64;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.daead.DeterministicAeadConfig;

public class DAEAD {
	
	public static void main(String[] args) throws GeneralSecurityException {
		
		
		  DeterministicAeadConfig.register();
		 // 1. Generate the key material.
	    KeysetHandle keysetHandle = KeysetHandle.generateNew(
	        KeyTemplates.get("AES256_SIV"));

	    // 2. Get the primitive.
	    DeterministicAead daead =
	       keysetHandle.getPrimitive(DeterministicAead.class);

	    // 3. Use the primitive to deterministically encrypt a plaintext,
	    byte[] ciphertext1 = daead.encryptDeterministically("jitendra".getBytes(),null);
	    byte[] ciphertext2 = daead.encryptDeterministically("jitendra".getBytes(),null);
	  
	    System.out.println("ad jk cipherText:"+Base64.getEncoder().encodeToString(ciphertext1));
	    System.out.println("ad kj cipherText:"+Base64.getEncoder().encodeToString(ciphertext2));

	    // ... or to deterministically decrypt a ciphertext.
	    byte[] decrypted1 = daead.decryptDeterministically(ciphertext1,null);
	  //  byte[] decrypted2 = daead.decryptDeterministically(ciphertext2, "kj".getBytes());
	    
	    System.out.println("jk:"+new String(decrypted1));
	   // System.out.println("kj:"+new String(decrypted2));
	}

}
