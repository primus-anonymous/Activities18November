package com.neocaptainnemo.activities18november.domain;

public class CalculatorImpl implements Calculator {
    @Override
    public double performOperation(double argOne, double argTwo, Operation operation) {
        switch (operation) {
            case SUM:
                return argOne + argTwo;
        }
        return 0;
    }
}
