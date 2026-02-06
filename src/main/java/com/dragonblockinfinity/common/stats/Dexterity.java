package com.dragonblockinfinity.common.stats;

public class Dexterity {
    private double value;

    public Dexterity(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // Multiplicador base de Dexteridade
    public double getMultiplier() {
        return 1 + (value * 0.01); // Multiplicador baseado no valor de Dexterity
    }

    // Defesa passiva (redução de dano)
    public double getPassiveDefense() {
        return value * 0.5; // Redução passiva de dano
    }

    // Redução de dano passiva
    public double getDamageReduction() {
        return (value * 0.5) / 100.0; // Reduz dano em 0.5% por ponto de Dexterity
    }

    public double calculateDodgeChance(double enemyDamage) {
        if (value >= 2.9 * enemyDamage) {
            return 7.0 * getMultiplier(); // Multiplica a esquiva
        }
        return 0.0;
    }

    public double calculateKnockbackReduction(double enemyDamage) {
        if (value >= 2.9 * enemyDamage) {
            return 0.7 * getMultiplier(); // Multiplica a redução de knockback
        }
        return 1.0; // Sem redução
    }
}