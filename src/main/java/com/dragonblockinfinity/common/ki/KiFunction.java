package com.dragonblockinfinity.common.ki;

import com.dragonblockinfinity.common.forms.BaseForm;

public class KiFunction {
    private int ki;
    private int positiveKi;
    private int negativeKi;
    private int maxKi = 100; // Maximum Ki value
    private int minKi = 0;   // Minimum Ki value
    private int baseMaxKi = 100; // Base maximum Ki, affected by status

    public KiFunction(int ki) {
        this.ki = ki;
        this.positiveKi = 0;
        this.negativeKi = 0;
    }

    public int getKi() {
        return ki;
    }

    public int getPositiveKi() {
        return positiveKi;
    }

    public int getNegativeKi() {
        return negativeKi;
    }

    public int getMaxKi() {
        return maxKi;
    }

    public void setBaseMaxKi(int baseMaxKi) {
        this.baseMaxKi = baseMaxKi;
        updateMaxKi();
    }

    public void updateMaxKi() {
        this.maxKi = baseMaxKi;
        if (this.maxKi < minKi) {
            this.maxKi = minKi;
        }
    }

    public void addPositiveKi(int amount) {
        this.positiveKi += amount;
    }

    public void addNegativeKi(int amount) {
        this.negativeKi += amount;
    }

    public void useKi(int amount) {
        if (ki >= amount) {
            ki -= amount;
        } else {
            throw new IllegalArgumentException("Not enough Ki to perform this action.");
        }
    }

    public void regenerateKi(int amount) {
        this.ki = Math.min(this.ki + amount, maxKi);
    }

    public void passiveRegeneration() {
        regenerateKi(5); // Regenerate 5 Ki per tick (example value)
    }

    public void checkKiThresholds() {
        if (ki <= 10) {
            triggerLowKiEvent();
        } else if (ki >= maxKi - 10) {
            triggerHighKiEvent();
        }
    }

    private void triggerLowKiEvent() {
        System.out.println("Warning: Ki is critically low!");
        // Additional logic for low Ki event
    }

    private void triggerHighKiEvent() {
        System.out.println("Ki is at maximum capacity!");
        // Additional logic for high Ki event
    }
}