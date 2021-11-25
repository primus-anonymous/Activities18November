package com.neocaptainnemo.activities18november.domain;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.neocaptainnemo.activities18november.R;

public enum Theme {

    ONE(R.style.Theme_Activities18November, R.string.theme_one, "one"),
    TWO(R.style.Theme_Activities18November_Second, R.string.theme_two, "two"),
    THREE(R.style.Theme_Activities18November_Third, R.string.theme_three, "three"),
    FOUR(R.style.Theme_Activities18November_Fourth, R.string.theme_four, "four");

    @StyleRes
    private final int theme;

    @StringRes
    private final int name;

    private String key;

    Theme(int theme, int name, String key) {
        this.theme = theme;
        this.name = name;
        this.key = key;
    }

    public int getTheme() {
        return theme;
    }

    public int getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
