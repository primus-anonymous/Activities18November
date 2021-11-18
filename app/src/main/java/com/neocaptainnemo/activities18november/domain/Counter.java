package com.neocaptainnemo.activities18november.domain;

import java.io.Serializable;

public class Counter implements Serializable {

    private int value;

    public Counter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void increase() {
        value++;
    }
}
