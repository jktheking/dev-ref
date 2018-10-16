package edu.jktheking.cryptography.sample;

import java.util.Date;

public class CryptoExchangeDecryptedPayload {

	private Date keyExchangeDate;
	private String symmetricKey;
	private String hmacKey;

	public CryptoExchangeDecryptedPayload(Date keyExchangeDate, String symmetricKey, String hmacKey) {
		super();
		this.keyExchangeDate = keyExchangeDate;
		this.symmetricKey = symmetricKey;
		this.hmacKey = hmacKey;
	}

	public Date getKeyExchangeDate() {
		return keyExchangeDate;
	}

	public void setKeyExchangeDate(Date keyExchangeDate) {
		this.keyExchangeDate = keyExchangeDate;
	}

	public String getSymmetricKey() {
		return symmetricKey;
	}

	public void setSymmetricKey(String symmetricKey) {
		this.symmetricKey = symmetricKey;
	}

	public String getHmacKey() {
		return hmacKey;
	}

	public void setHmacKey(String hmacKey) {
		this.hmacKey = hmacKey;
	}

}
