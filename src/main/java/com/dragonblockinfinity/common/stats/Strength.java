package com.dragonblockinfinity.common.stats;

public class Strength {
    private double value;

    public Strength(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double calculateStaminaCost(double con, double stamina) {
        if (value > con) {
            return stamina * (1 + (value - con) * 0.1); // Gasto maior
        } else {
            return stamina * (1 - (con - value) * 0.05); // Gasto menor
        }
    }
}