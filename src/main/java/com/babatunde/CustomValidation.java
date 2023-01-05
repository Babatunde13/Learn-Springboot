package com.babatunde;

import java.util.regex.*;;

public class CustomValidation {
    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).find();
    }

    public static boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile("^[a-z0-9]{5,12}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(username).find();
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^[a-z0-9@.-]{8,20}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(password).find();
    }

    public static boolean isSlugValid(String slug) {
        Pattern pattern = Pattern.compile("^[a-z@0-9-]{8,20}$");
        return pattern.matcher(slug).find();
    }

    public static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("^\\d{11}$");
        return pattern.matcher(phone).find();
    }

    public static boolean isDateValid(String date) {
        Pattern pattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        return pattern.matcher(date).find();
    }

    public static void main(String[] args) {
        System.out.println(isEmailValid("koikibabatunde14@gmail.com"));
    }
}