package com.demoqa.utils;

import com.demoqa.entities.LoginEntity;
import com.demoqa.entities.RegisterEntity;
import com.demoqa.entities.ResetPasswordEntity;
import com.github.javafaker.Faker;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomUtils {

    private static final Faker faker = new Faker();

    public LoginEntity ValidLoginEntity() {
        LoginEntity entity = new LoginEntity();
        entity.setEmail("GOku.first@proton.me");
        entity.setPassword("AAAAA1!!CloseEye1");

        return entity;
    }

    // Генерация случайных данных для регистрации
    public RegisterEntity generateRandomRegisterEntity() {
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setFirstName(faker.name().firstName());
        registerEntity.setEmail(faker.internet().emailAddress());
        String password = generateRandomPassword();
        registerEntity.setPassword(password);
        registerEntity.setPassword2(password);
        return registerEntity;
    }

    // Генерация случайных данных для логина
    public LoginEntity generateRandomLoginEntity() {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setEmail(faker.internet().emailAddress());
        String password = generateRandomPassword();
        loginEntity.setPassword(password);
        return loginEntity;
    }

    // Метод для генерации случайных данных для сброса пароля
    public ResetPasswordEntity generateRandomResetPasswordEntity() {
        ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity();
        resetPasswordEntity.setEmail(faker.internet().emailAddress());
        String newPassword = generateRandomPassword();
        resetPasswordEntity.setPassword(newPassword);
        resetPasswordEntity.setPassword2(newPassword);
        return resetPasswordEntity;
    }

    // Генерация случайного пароля
    private String generateRandomPassword() {
        String digit = faker.regexify("[0-9]");
        String specialChar = faker.regexify("[!@#$%^&*()]");
        String upperCase = faker.regexify("[A-Z]");
        String remaining = faker.regexify("[a-zA-Z0-9!@#$%^&*()]{5,8}");

        // Собираем пароль и перемешиваем его
        String password = digit + specialChar + upperCase + remaining;

        return shuffleString(password); // Перемешиваем символы пароля
    }

    // Метод для перемешивания строки
    private String shuffleString(String input) {
        List<Character> characters = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(characters);
        StringBuilder shuffled = new StringBuilder();
        characters.forEach(shuffled::append);
        return shuffled.toString();
    }


}
