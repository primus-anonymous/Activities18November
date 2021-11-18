package com.neocaptainnemo.activities18november;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actvity_second);

        if (savedInstanceState == null) {
            logLifecycle("onCreate first");

        } else {
            logLifecycle("onCreate recreate");

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        logLifecycle("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        logLifecycle("onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();

        logLifecycle("onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();

        logLifecycle("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        logLifecycle("onDestroy");

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        logLifecycle("onSaveInstanceState");

    }

    private void logLifecycle(String toLog) {
        Log.d("logLifecycle2", toLog);
    }

}
