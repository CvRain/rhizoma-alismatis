package com.example.rhizoma_alismatis.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StrFormat {
    public static String sha256(final String text) {
        return sha(text, "SHA-256");
    }

    public static String sha512(final String text) {
        return sha(text, "SHA-512");
    }

    public static String md5(final String text) {
        return sha(text, "MD5");
    }

    private static String sha(final String text, final String algorithm) {
        String result = null;
        if (text == null || text.isEmpty()) {
            return "";
        }
        try {
            final MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(text.getBytes());

            byte[] bytebuffer = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : bytebuffer) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            result = hexString.toString();
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return result;
    }

}
