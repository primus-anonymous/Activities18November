package com.neocaptainnemo.activities18november.ui;

import com.neocaptainnemo.activities18november.domain.Calculator;
import com.neocaptainnemo.activities18november.domain.Operation;

public class CalculatorPresenter {

    private CalculatorView view;
    private Calculator calculator;

    private Double argOne = 0.0;
    private Double argTwo = null;
    private Operation previousOperation = null;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void onDotPressed() {

    }

    public void onDigitPressed(int digit) {

        if (previousOperation != null) {
            argTwo = argTwo * 10 + digit;

            view.showResult(String.valueOf(argTwo));

        } else {
            argOne = argOne * 10 + digit;

            view.showResult(String.valueOf(argOne));

        }
    }

    public void onOperandPressedPressed(Operation operation) {

        if (argTwo != null) {
            double result = calculator.performOperation(argOne, argTwo, previousOperation);

            view.showResult(String.valueOf(result));

            argOne = result;
            argTwo = 0.0;
        } else {
            argTwo = 0.0;
            previousOperation = operation;
        }

    }
}
