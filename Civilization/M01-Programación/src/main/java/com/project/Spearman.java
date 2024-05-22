package com.project;

public class Spearman extends AttackUnit {
    public Spearman(int armor, int baseDamage){
        super.armor = ARMOR_SPEARMAN + (PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * armor) * 1000/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_SPEARMAN + (PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * baseDamage) * 1000/100;
        super.experience = 0;
        super.sanctified = false;
    }

    public Spearman() {
        super.armor = ARMOR_SPEARMAN;
        super.initialArmor = ARMOR_SPEARMAN;
        super.baseDamage =  BASE_DAMAGE_SPEARMAN;
        super.experience = 0;
        super.sanctified = false;
    }

    public int attack(){
        return super.baseDamage;
    }

    public void takeDamage(int receivedDamage){
        super.armor -= receivedDamage;
    }

    public int getActualArmor(){
        return super.armor;
    }

    public int getFoodCost(){
        return FOOD_COST_SPEARMAN;
    }

    public int getWoodCost(){
        return WOOD_COST_SPEARMAN;
    }

    public int getIronCost(){
        return IRON_COST_SPEARMAN;
    }

    public int getManaCost(){
        return MANA_COST_SPEARMAN;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_SPEARMAN;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_SPEARMAN;
    }

    public void resetArmor(){
        super.armor = super.initialArmor;
    }

    public void setExperience(int n){
        super.experience = n;
    }

    public int getExperience(){
        return super.experience;
    }
}
