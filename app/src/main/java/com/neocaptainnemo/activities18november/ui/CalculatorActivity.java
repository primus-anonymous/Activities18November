package com.neocaptainnemo.activities18november.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.neocaptainnemo.activities18november.R;
import com.neocaptainnemo.activities18november.domain.CalculatorImpl;
import com.neocaptainnemo.activities18november.domain.Operation;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView txtResult;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public void showResult(String value) {
        txtResult.setText(value);
    }
}