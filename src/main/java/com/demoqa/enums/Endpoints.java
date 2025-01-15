package com.demoqa.enums;

import lombok.Getter;

public enum Endpoints {

    SIGNUP("/sign-up"),
    SIGNIN("/sign-in"),
    RESET("/sign-in/reset-password"),
    SEARCH("/search"),
    TRIPS("/trips"),
    SUPPORT("/support"),
    POLICY("/policy");


    @Getter
    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return this.endpoint;
    }
}
