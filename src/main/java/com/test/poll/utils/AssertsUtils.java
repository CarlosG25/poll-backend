package com.test.poll.utils;


import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;

@Service
public class AssertsUtils {

    public void isNotNull(Object value, String message) {
       Assert.notNull(value, message);
    }

    public void isNotNullOrEmpty(Object value, String nameParameter) {
        this.isNotNull(value,  nameParameter + " cannot be null!.");
        this.isNotEmpty((String) value, nameParameter);
    }

    public void isNotEmpty(String value, String nameParameter) {
        Assert.notEmpty(Collections.singleton(value), nameParameter + " is empty!.");
    }

}
