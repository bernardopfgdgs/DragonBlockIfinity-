package com.dragonblockinfinity.common.stats;

public class PlayerStats {
    private double str; // Força
    private double spi; // Ki Máximo
    private double will; // Força do ataque de Ki
    private double mnd; // Moeda para upar skills e formas
    private double con; // Vida e Stamina Máxima
    private double dex; // Velocidade e Vida

    // Construtor
    public PlayerStats(double str, double spi, double will, double mnd, double con, double dex) {
        this.str = str;
        this.spi = spi;
        this.will = will;
        this.mnd = mnd;
        this.con = con;
        this.dex = dex;
    }

    // Getters e Setters
    public double getStr() {
        return str;
    }

    public void setStr(double str) {
        this.str = str;
    }

    public double getSpi() {
        return spi;
    }

    public void setSpi(double spi) {
        this.spi = spi;
    }

    public double getWill() {
        return will;
    }

    public void setWill(double will) {
        this.will = will;
    }

    public double getMnd() {
        return mnd;
    }

    public void setMnd(double mnd) {
        this.mnd = mnd;
    }

    public double getCon() {
        return con;
    }

    public void setCon(double con) {
        this.con = con;
    }

    public double getDex() {
        return dex;
    }

    public void setDex(double dex) {
        this.dex = dex;
    }

    // Cálculo de gasto de stamina baseado em Str e Con
    public double calculateStaminaCost(double stamina) {
        if (str > con) {
            return stamina * (1 + (str - con) * 0.1); // Gasto maior
        } else {
            return stamina * (1 - (con - str) * 0.05); // Gasto menor
        }
    }

    // Cálculo de esquiva baseado em Dex
    public double calculateDodgeChance(double enemyDamage) {
        if (dex >= 2.9 * enemyDamage) {
            return 7.0; // 7% de esquiva
        }
        return 0.0;
    }

    // Redução de knockback baseado em Dex
    public double calculateKnockbackReduction(double enemyDamage) {
        if (dex >= 2.9 * enemyDamage) {
            return 0.7; // Reduz knockback em 30%
        }
        return 1.0; // Sem redução
    }

    // Anulação de dano e knockback baseado em defesa real e stamina
    public boolean isDamageAndKnockbackNullified(double enemyDamage, double currentStamina) {
        if (con > enemyDamage && currentStamina > 0) {
            return true; // Dano e knockback anulados
        }
        return false;
    }
}