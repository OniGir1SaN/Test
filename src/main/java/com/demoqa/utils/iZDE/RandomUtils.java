package com.demoqa.utils.iZDE;

import com.demoqa.entities.iZDE.*;
import com.demoqa.utils.ConfigReader;
import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {

    private static final Faker faker = new Faker();
    private static final Random random = new SecureRandom();

    public LoginEntity validLoginEntity() {
        LoginEntity entity = new LoginEntity();
        entity.setEmail(ConfigReader.getValue("login"));
        entity.setPassword(ConfigReader.getValue("password"));
        return entity;
    }

    public RegisterEntity generateRandomRegisterEntity() {
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setFirstName(faker.name().firstName());
        registerEntity.setEmail(faker.internet().emailAddress());
        String password = generateRandomPassword();
        registerEntity.setPassword(password);
        registerEntity.setPassword2(password);
        return registerEntity;
    }

    public LoginEntity generateRandomLoginEntity() {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setEmail(faker.internet().emailAddress());
        loginEntity.setPassword(generateRandomPassword());
        return loginEntity;
    }

    public ProfileEntity generateRandomProfileNameEntity() {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setFirstName(faker.name().firstName());
        profileEntity.setLastName(faker.name().lastName());
        profileEntity.setAvatar(faker.internet().avatar());
        return profileEntity;
    }

    public ChangeEmailEntity generateRandomChangeEntityForm() {
        ChangeEmailEntity changeEmailEntity = new ChangeEmailEntity();
        changeEmailEntity.setEmail(faker.internet().emailAddress());
        return changeEmailEntity;
    }

    public ChangePasswordEntity generateRandomChangePasswordEntity() {
        ChangePasswordEntity changePasswordEntity = new ChangePasswordEntity();
        changePasswordEntity.setCurrentPassword(generateRandomPassword());
        changePasswordEntity.setNewPassword(generateRandomPassword());
        changePasswordEntity.setRepeatNewPassword(changePasswordEntity.getNewPassword());
        return changePasswordEntity;
    }

    public ResetNumberEntity generateRandomResetNumberEntity() {
        ResetNumberEntity resetNumberEntity = new ResetNumberEntity();
        resetNumberEntity.setNumber(100000 + random.nextInt(900000));
        resetNumberEntity.setPassword(generateRandomPassword());
        return resetNumberEntity;
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
