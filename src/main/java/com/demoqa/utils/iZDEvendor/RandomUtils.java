package com.demoqa.utils.iZDEvendor;

import com.demoqa.entities.iZDE.*;
import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {

    private static final Faker faker = new Faker();
    private static final Random random = new SecureRandom();

    public LoginEntity validLoginEntity() {
        LoginEntity entity = new LoginEntity();
        entity.setEmail("GOku.first@proton.me");
        entity.setPassword("AAAAA1!!CloseEye1");
        return entity;
    }

    public static String generateRandomPassword() {
        String upperCase = getRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 2);
        String lowerCase = getRandomChars("abcdefghijklmnopqrstuvwxyz", 2);
        String digits = getRandomChars("0123456789", 2);
        String specialChars = getRandomChars("!@#$%^&*()_+", 2);
        String remaining = getRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+", 4);
        return shuffleString(upperCase + lowerCase + digits + specialChars + remaining);
    }

    private static String getRandomChars(String characters, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private static String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }
}
