package com.dougfsilva.e_AGE.infrastructure;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;

import com.dougfsilva.e_AGE.domain.exception.OccurrenceSignatureOperationException;
import com.dougfsilva.e_AGE.domain.occurrence.Occurrence;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceSignature;
import com.dougfsilva.e_AGE.domain.occurrence.OccurrenceSignatureCryptography;

public class OccurrenceSignerMD implements OccurrenceSignatureCryptography {

	private static final Integer SALT_LEGHT = 32;
	private String secretKey;
	private final SecureRandom secureRandom;

	public OccurrenceSignerMD(@Value("${eage.occ.secret.key}") String secretKey, SecureRandom secureRandom) {
		if(secretKey == null || secretKey.isBlank()) {
			throw new OccurrenceSignatureOperationException("Secret Key cannot be null or empty!");
		}
		this.secretKey = secretKey;
		this.secureRandom = secureRandom;
	}

	@Override
	public OccurrenceSignature generateSignature(Occurrence occurrence) {
		String salt = generateSalt();
		LocalDateTime timestamp = LocalDateTime.now();
		String dataToHash = occurrence.getStudent().getID() + occurrence.getID() + timestamp.toString() + salt + secretKey;
		String hash = generateHash(dataToHash);
		return new OccurrenceSignature(hash, timestamp, salt);
	}

	@Override
	public boolean validateSignature(Occurrence occurrence) {
		String dataToHash = occurrence.getStudent().getID() + occurrence.getID()
				+ occurrence.getStudentSignature().getTimestamp().toString()
				+ occurrence.getStudentSignature().getSalt() + secretKey;
		String generatedHash = generateHash(dataToHash);
		return generatedHash.equals(occurrence.getStudentSignature().getSignature());
	}

	private String generateSalt() {
		byte[] salt = new byte[SALT_LEGHT];
		secureRandom.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	private String generateHash(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hashBytes);
		} catch (NoSuchAlgorithmException | OccurrenceSignatureOperationException e) {
			throw new OccurrenceSignatureOperationException("Error generating signature hash", e);
		}
	}

}
