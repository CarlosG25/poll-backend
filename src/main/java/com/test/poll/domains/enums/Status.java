package com.test.poll.domains.enums;

public enum Status {

    ACTIVE("A", "ACTIVE"),
    INACTIVE("I", "INACTIVE");

    String value;
    String name;

    Status(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
