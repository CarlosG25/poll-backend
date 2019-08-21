package com.test.poll.utils;

import com.test.poll.config.GlobalConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class PasswordUtils {

    @Autowired
    private GlobalConfiguration globalConfiguration;

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    private  byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(this.globalConfiguration.getSecretPassword());
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password_j: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public  String generateSecurePassword(String password) {
        byte[] securePassword = this.hash(password.toCharArray(), globalConfiguration.getSaltPassword().getBytes());

        return Base64.getEncoder().encodeToString(securePassword);
    }

    public  boolean verifyUserPassword(String providedPassword,
                                             String securedPassword) {

        String newSecurePassword = this.generateSecurePassword(providedPassword);
        return newSecurePassword.equalsIgnoreCase(securedPassword);

    }
}