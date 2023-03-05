package com;

public class Main {

    public static void main(String[] args) {

        try {
            new ValueCalculator().doCalc(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
