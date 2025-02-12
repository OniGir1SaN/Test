package com.demoqa.enums.iZDE;

import lombok.Getter;

public enum Endpoints {

    SIGNUP("sign-up"),
    SIGNIN("sign-in"),
    RESET("sign-in/reset-password"),
    NEW_PASSWORD("sign-in/reset-password/change"),
    SEARCH("search"),
    TRIPS("trips"),
    SUPPORT("support"),
    PROFILE("profile"),
    POLICY("policy");

    @Getter
    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return this.endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
