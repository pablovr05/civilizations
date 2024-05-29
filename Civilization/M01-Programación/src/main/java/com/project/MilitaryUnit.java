package com.project;

public abstract class MilitaryUnit {
    abstract int attack();
    abstract void takeDamage(int receivedDamage);
    abstract int getActualArmor();
    abstract int getFoodCost();
    abstract int getWoodCost();
    abstract int getIronCost();
    abstract int getManaCost();
    abstract int getChanceGeneratingWaste();
    abstract int getChanceAttackAgain();
    abstract void resetArmor();
    abstract void setExperience(int n);
    abstract int getExperience();
    abstract void sanctify();
    abstract void setBaseDamage(int damage);
    abstract int getSanctified();
    abstract void setInitialArmor(int initialArmor);
}
