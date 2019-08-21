package com.test.poll.domains.enums;

public enum Rol {

    ADMIN("ADMIN", "Admin"),
    USER("USER", "User");

    String value;
    String name;

    Rol(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
