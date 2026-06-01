package com.example.Util;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_VN_PATTERN = "^(0|\\+84)(3|5|7|8|9)[0-9]{8}$";

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public static boolean isValidPhoneVN(String phone) {
        if (phone == null) return false;
        return Pattern.matches(PHONE_VN_PATTERN, phone);
    }
}