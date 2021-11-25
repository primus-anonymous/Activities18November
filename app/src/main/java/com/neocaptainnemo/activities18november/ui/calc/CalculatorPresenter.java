package com.neocaptainnemo.activities18november.ui.calc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.neocaptainnemo.activities18november.domain.Calculator;
import com.neocaptainnemo.activities18november.domain.Operation;

public class CalculatorPresenter {

    private static final String KEY_STATE = "KEY_STATE";

    private final CalculatorView view;
    private final Calculator calculator;

    private Double argOne = 0.0;
    private Double argTwo = null;
    private Operation previousOperation = null;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onSaveState(Bundle bundle) {
        bundle.putParcelable(KEY_STATE, new State(argOne, argTwo, previousOperation));
    }

    public void restoreState(Bundle bundle) {
        State state = bundle.getParcelable(KEY_STATE);

        argOne = state.argOne;

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

    static class State implements Parcelable {
        public static final Creator<State> CREATOR = new Creator<State>() {
            @Override
            public State createFromParcel(Parcel in) {
                return new State(in);
            }

            @Override
            public State[] newArray(int size) {
                return new State[size];
            }
        };

        public State(Double argOne, Double argTwo, Operation previousOperation) {
            this.argOne = argOne;
            this.argTwo = argTwo;
            this.previousOperation = previousOperation;
        }

        private Double argOne = 0.0;
        private Double argTwo = null;
        private Operation previousOperation = null;

        public Double getArgOne() {
            return argOne;
        }

        public Double getArgTwo() {
            return argTwo;
        }

        public Operation getPreviousOperation() {
            return previousOperation;
        }

        protected State(Parcel in) {
            if (in.readByte() == 0) {
                argOne = null;
            } else {
                argOne = in.readDouble();
            }
            if (in.readByte() == 0) {
                argTwo = null;
            } else {
                argTwo = in.readDouble();
            }
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (argOne == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(argOne);
            }
            if (argTwo == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(argTwo);
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }
    }
}
