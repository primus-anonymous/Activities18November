package com.neocaptainnemo.activities18november;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.neocaptainnemo.activities18november.domain.Counter;
import com.neocaptainnemo.activities18november.domain.CounterSecond;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ARG_COUNT = "ARG_COUNT";
    private static final String ARG_SECOND_COUNT = "ARG_SECOND_COUNT";
    private static final String ARG_THIRD_COUNT = "ARG_THIRD_COUNT";

    private int count = 0;

    private Counter counter = new Counter(0);

    private CounterSecond counterSecond = new CounterSecond(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            logLifecycle("onCreate first");

        } else {
            logLifecycle("onCreate recreate");

            count = savedInstanceState.getInt(ARG_COUNT);
            counter = (Counter) savedInstanceState.getSerializable(ARG_SECOND_COUNT);
            counterSecond = savedInstanceState.getParcelable(ARG_THIRD_COUNT);
        }

        Intent intent = new Intent(this, SecondActivity.class);

        findViewById(R.id.go_to_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        TextView countValue = findViewById(R.id.count_value);
        countValue.setText(String.valueOf(count));

        TextView countSecondValue = findViewById(R.id.count_value_second);
        countSecondValue.setText(String.valueOf(counter.getValue()));

        TextView countThirdValue = findViewById(R.id.count_value_third);
        countThirdValue.setText(String.valueOf(counterSecond.getValue()));

        findViewById(R.id.count_increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                countValue.setText(String.valueOf(count));

            }
        });

        findViewById(R.id.count_increase_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter.increase();
                countSecondValue.setText(String.valueOf(counter.getValue()));

            }
        });

        findViewById(R.id.count_increase_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counterSecond.increase();
                countThirdValue.setText(String.valueOf(counterSecond.getValue()));

            }
        });

        ClickListenerHandler handler = new ClickListenerHandler();


        countValue.setOnClickListener(this);
        countSecondValue.setOnClickListener(handler);
        countThirdValue.setOnClickListener(handler);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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

        outState.putInt(ARG_COUNT, count);
        outState.putSerializable(ARG_SECOND_COUNT, counter);
        outState.putParcelable(ARG_THIRD_COUNT, counterSecond);

        logLifecycle("onSaveInstanceState");

    }

    private void logLifecycle(String toLog) {
        Log.d("logLifecycle", toLog);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.count_value_second:

                break;
        }

    }

    private static class ClickListenerHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}