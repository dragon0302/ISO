package com.example.iso16;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class EncodingPassword {

  private final int ITERATION = 10000;
  private final int KEY_LENGTH = 256;
  private final String ALGORITHM = "PBKDF2WithHmacSHA256";

  public String genereitSalt(){
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }

  public String codePassword(String password, String salt){
    try {
      PBEKeySpec spec = new PBEKeySpec(
              password.toCharArray(),
              Base64.getDecoder().decode(salt),
              ITERATION,
              KEY_LENGTH
      );

      SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
      byte[] hash = factory.generateSecret(spec).getEncoded();
      return Base64.getEncoder().encodeToString(hash);
    }catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException("Error hashing password", e);
    }
  }

  public boolean verifyPassword(String password, String storedHash, String salt)
          throws NoSuchAlgorithmException, InvalidKeySpecException {

    // 1. Decodifica il salt da Base64 a byte[]
    byte[] saltBytes = Base64.getDecoder().decode(salt);

    // 2. Configura PBKDF2
    PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(),
            saltBytes,  // Usa saltBytes, non saltedHash!
            ITERATION,
            KEY_LENGTH
    );

    // 3. Genera l'hash della password inserita
    SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
    byte[] testHash = factory.generateSecret(spec).getEncoded();

    // 4. Converti in Base64 per confronto
    String computedHash = Base64.getEncoder().encodeToString(testHash);

    // 5. Confronta con l'hash memorizzato
    return computedHash.equals(storedHash);
  }

}
