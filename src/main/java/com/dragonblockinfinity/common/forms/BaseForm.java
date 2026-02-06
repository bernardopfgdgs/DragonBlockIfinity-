package com.dragonblockinfinity.common.forms;

public class BaseForm {
   public String formNameBase;
   public String formDescriptionBase;
   
   public String getFormNameBase() {
      return formNameBase;
   }
   
   public String getFormDescriptionBase() {
      return formDescriptionBase;
   }
   private double masteryFormBase;
   
   public double getMasteryFormBase() {
      return this.masteryFormBase;
   }
   public void setMasteryFormBase(double masteryFormBase) {
    this.masteryFormBase = masteryFormBase;
   }
   
   public void addMasteryFormBase(double ganho) {
      this.masteryFormBase += ganho;
   }
   public double getMultiplierstats() {
      return 1 + (masteryFormBase * 0.005);
   }
   public void gainMasteryOnHit() {
      this.masteryFormBase += 0.1;
   }
   public void gainMasteryOnDamage(double damage) {
      double ganho = damage * 0.01;
      this.masteryFormBase += ganho;
   }
   public void capMasteryFormBase() {
   if (this.masteryFormBase > 100) {
      this.masteryFormBase = 100;
   }
}
   
}