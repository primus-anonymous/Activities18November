package com.neocaptainnemo.activities18november.ui.calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.neocaptainnemo.activities18november.R;
import com.neocaptainnemo.activities18november.SuperReporitory;
import com.neocaptainnemo.activities18november.domain.CalculatorImpl;
import com.neocaptainnemo.activities18november.domain.Operation;
import com.neocaptainnemo.activities18november.domain.Theme;
import com.neocaptainnemo.activities18november.storage.ThemeStorage;
import com.neocaptainnemo.activities18november.ui.theme.SelectThemeActivity;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Theme theme = (Theme) result.getData().getSerializableExtra(SelectThemeActivity.EXTRA_THEME);

                storage.saveTheme(theme);

                recreate();
            }
        }
    });
    private TextView txtResult;

    // private int currentTheme = R.style.Theme_Activities18November_Second;
    private CalculatorPresenter presenter;

    private ThemeStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);

        Context me = this;

        Context applicationContext = getApplicationContext();

        SuperReporitory superReporitory = SuperReporitory.getInstance(getApplicationContext());

//        LayoutInflater.from(me).inflate(R.layout.activity_calculator, null);
//        LayoutInflater.from(applicationContext).inflate(R.layout.activity_calculator, null);


//        if (savedInstanceState != null) {
//            currentTheme = savedInstanceState.getInt(ARG_SAVED_THEME);
//        }

        setTheme(storage.getSavedTheme().getTheme());

        setContentView(R.layout.activity_calculator);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }

        txtResult = findViewById(R.id.result);

        if (getIntent().hasExtra("hello")) {
            txtResult.setText(getIntent().getStringExtra("hello"));
        }

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


        findViewById(R.id.choose_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, storage.getSavedTheme());

                launcher.launch(intent);
            }
        });

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        presenter.onSaveState(outState);

    }

    @Override
    public void showResult(String value) {
        txtResult.setText(value);
    }
}