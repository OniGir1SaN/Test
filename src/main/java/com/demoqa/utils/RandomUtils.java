package com.demoqa.utils;

import com.demoqa.entities.iZDE.*;
import com.github.javafaker.Faker;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomUtils {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public LoginEntity validLoginEntity() {
        LoginEntity entity = new LoginEntity();
        entity.setEmail("GOku.first@proton.me");
        entity.setPassword("AAAAA1!!CloseEye1");
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
        String password = generateRandomPassword();
        loginEntity.setPassword(password);
        return loginEntity;
    }

    public ProfileEntity generateRandomProfileNameEntity(){
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setFirstName(faker.name().firstName());
        profileEntity.setLastName(faker.name().lastName());
        profileEntity.setAvatar(faker.internet().avatar());
        return profileEntity;
    }

    public ChangeEmailEntity generateRandomChangeEntityForm(){
        ChangeEmailEntity changeEmailEntity = new ChangeEmailEntity();
        changeEmailEntity.setEmail(faker.internet().emailAddress());
        return changeEmailEntity;
    }

    // Метод для генерации случайных данных для изменения пароля
    public ChangePasswordEntity generateRandomChangePasswordEntity() {
        ChangePasswordEntity changePasswordEntity = new ChangePasswordEntity();
        String newPassword = generateRandomPassword();
        changePasswordEntity.setCurrentPassword(newPassword);
        changePasswordEntity.setRepeatNewPassword(newPassword);
        return changePasswordEntity;
    }

    // Метод для генерации случайных данных для сброса пароля
    public static ResetNumberEntity generateRandomResetNumberEntity() {
        ResetNumberEntity resetNumberEntity = new ResetNumberEntity();
        resetNumberEntity.setNumber(random.nextInt(10000)); // Генерация случайного числа от 0 до 9999
        resetNumberEntity.setPassword(generateRandomPassword()); // Генерация случайного пароля
        return resetNumberEntity;
    }

    // Генерация случайного пароля с конкретными требованиями
    public static String generateRandomPassword() {
        String password = generatePasswordWithRequirements(1, 1, 1, 1, 5, 8);
        return shuffleString(password);
    }

    // Генерация пароля с конкретным количеством символов
    public static String generatePasswordWithRequirements(int digits, int specialChars, int upperCases, int lowerCases, int minLength, int maxLength) {
        String digit = generateRandomString(digits, "[0-9]");
        String specialChar = generateRandomString(specialChars, "[!@#$%^&*()]");
        String upperCase = generateRandomString(upperCases, "[A-Z]");
        String lowerCase = generateRandomString(lowerCases, "[a-z]");
        String remaining = generateRandomString(random.nextInt(maxLength - minLength + 1) + minLength, "[a-zA-Z0-9!@#$%^&*()]");

        return digit + specialChar + upperCase + lowerCase + remaining;
    }

    // Генерация случайной строки с использованием регулярного выражения
    private static String generateRandomString(int length, String regex) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(faker.regexify(regex));
        }
        return result.toString();
    }

    // Метод для перемешивания строки
    private static String shuffleString(String input) {
        List<Character> characters = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(characters);
        StringBuilder shuffled = new StringBuilder();
        characters.forEach(shuffled::append);
        return shuffled.toString();
    }
}
