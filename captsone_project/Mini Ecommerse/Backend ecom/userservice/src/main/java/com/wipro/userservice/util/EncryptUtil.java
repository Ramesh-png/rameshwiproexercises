package com.wipro.userservice.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptUtil {
	// Hash password with BCrypt
    public static String getEncryptedPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Check password against stored hash
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public static void main(String[] args) {
        String hash = getEncryptedPassword("pass");
        System.out.println("Hash: " + hash);
        System.out.println("Match: " + checkPassword("pass", hash)); // should print true
    }
}
