package com.dragonblockinfinity.common.stats;

public class Constitution {
    private double value;

    public Constitution(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isDamageAndKnockbackNullified(double enemyDamage, double currentStamina) {
        if (value > enemyDamage && currentStamina > 0) {
            return true; // Dano e knockback anulados
        }
        return false;
    }
}