package com.siziksu.payment.data.utils;

import com.siziksu.payment.common.utils.Print;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Cryptography {

    private static final String MD5 = "MD5";

    public static String md5(final String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(string.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte message : messageDigest) {
                String h = Integer.toHexString(0xFF & message);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Print.error(e.getMessage(), e);
        }
        return "";
    }
}
