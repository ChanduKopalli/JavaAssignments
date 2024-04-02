package com.companyname.stringquestions;

import java.io.FileWriter;
import java.io.IOException;
interface Operation {
    double apply(double num1, double num2);
    String getOperator();
}

class Addition implements Operation {
    @Override
    public double apply(double num1, double num2) {
        return num1 + num2;
    }

    @Override
    public String getOperator() {
        return "+";
    }
}

class Subtraction implements Operation {
    @Override
    public double apply(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public String getOperator() {
        return "-";
    }
}

class Multiplication implements Operation {
    @Override
    public double apply(double num1, double num2) {
        return num1 * num2;
    }

    @Override
    public String getOperator() {
        return "*";
    }
}

class Division implements Operation {
    @Override
    public double apply(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return num1 / num2;
    }

    @Override
    public String getOperator() {
        return "/";
    }
}

class OperationFactory {
    public static Operation createOperation(char operator) {
        switch (operator) {
            case '+':
                return new Addition();
            case '-':
                return new Subtraction();
            case '*':
                return new Multiplication();
            case '/':
                return new Division();
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

public class Calculator {
    public static double calculate(double num1, double num2, char operator) {
        Operation operation = OperationFactory.createOperation(operator);
        try {
            return operation.apply(num1, num2);
        } catch (ArithmeticException e) {
            logException(e);
            throw e;
        }
    }

    private static void logException(ArithmeticException e) {
        try (FileWriter writer = new FileWriter("calculator_log.txt", true)) {
            writer.write("Exception occurred: " + e.getMessage() + "\n");
        } catch (IOException ioException) {
            System.err.println("Error occurred while writing to log file: " + ioException.getMessage());
        }
    }

    public static void main(String[] args) {
        double num1 = 10;
        double num2 = 0;
        char operator = '/';
        try {
            double result = calculate(num1, num2, operator);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
