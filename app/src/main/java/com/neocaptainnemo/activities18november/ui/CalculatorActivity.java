package com.neocaptainnemo.activities18november.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.neocaptainnemo.activities18november.R;
import com.neocaptainnemo.activities18november.domain.CalculatorImpl;
import com.neocaptainnemo.activities18november.domain.Operation;

import java.util.HashMap;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String ARG_SAVED_THEME = "ARG_SAVED_THEME";
    private TextView txtResult;

    private CalculatorPresenter presenter;

    // private int currentTheme = R.style.Theme_Activities18November_Second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (savedInstanceState != null) {
//            currentTheme = savedInstanceState.getInt(ARG_SAVED_THEME);
//        }

        setTheme(getSavedTheme());

        setContentView(R.layout.activity_calculator);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        txtResult = findViewById(R.id.result);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDotPressed();
            }
        });

        HashMap<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_0, 0);
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDigitPressed(digits.get(v.getId()));

            }
        };

        findViewById(R.id.key_0).setOnClickListener(digitClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);


        HashMap<Integer, Operation> operands = new HashMap<>();
        operands.put(R.id.key_plus, Operation.SUM);
        operands.put(R.id.key_minus, Operation.SUB);
        operands.put(R.id.key_div, Operation.DIV);
        operands.put(R.id.key_mult, Operation.MULT);

        View.OnClickListener operandClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOperandPressedPressed(operands.get(v.getId()));
            }
        };

        findViewById(R.id.key_mult).setOnClickListener(operandClickListener);
        findViewById(R.id.key_plus).setOnClickListener(operandClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operandClickListener);
        findViewById(R.id.key_div).setOnClickListener(operandClickListener);

        Locale locale = getResources().getConfiguration().locale;
        int orientaion = getResources().getConfiguration().orientation;

        if (orientaion == Configuration.ORIENTATION_LANDSCAPE) {

        } else {

        }

        String appName = getResources().getString(R.string.app_name);
        String appName2 = getString(R.string.app_name);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        view.findViewById(R.id.go_to_second);

        float fontSize = getResources().getDimension(R.dimen.calc_font_size);

        //txtResult.setTextSize(fontSize);

        CheckBox one = findViewById(R.id.change_theme_one);
        CheckBox two = findViewById(R.id.change_theme_two);
        CheckBox three = findViewById(R.id.change_theme_three);

        if (one != null) {
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    one.setChecked(true);
                    two.setChecked(false);
                    three.setChecked(false);

                    saveTheme(R.style.Theme_Activities18November);

                    recreate();

                }
            });
        }

        if (two != null) {
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    one.setChecked(false);
                    two.setChecked(true);
                    three.setChecked(false);

                    saveTheme(R.style.Theme_Activities18November_Second);

                    recreate();

                }
            });
        }

        if (three != null) {
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    one.setChecked(false);
                    two.setChecked(false);
                    three.setChecked(true);

                    saveTheme(R.style.Theme_Activities18November_Third);

                    recreate();

                }
            });
        }
    }

    private void saveTheme(int theme) {
        SharedPreferences sharedPreferences = getSharedPreferences("Themes", Context.MODE_PRIVATE);

        sharedPreferences.edit()
                .putInt(ARG_SAVED_THEME, theme)
                .apply();
    }

    private int getSavedTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences("Themes", Context.MODE_PRIVATE);

        return sharedPreferences.getInt(ARG_SAVED_THEME, R.style.Theme_Activities18November);
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putInt(ARG_SAVED_THEME, currentTheme);
//    }

    @Override
    public void showResult(String value) {
        txtResult.setText(value);
    }
}