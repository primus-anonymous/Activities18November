package com.neocaptainnemo.activities18november;

import android.content.Context;

public class SuperReporitory {

    private static SuperReporitory INSTANCE;

    public static SuperReporitory getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SuperReporitory(context);
        }
        return INSTANCE;
    }

    private Context context;

    private SuperReporitory(Context context) {
        this.context = context;
    }

    public void doSomething() {

       String appName = context.getString(R.string.app_name);
    }
}
