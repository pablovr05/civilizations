package com.project;

public class Cannon extends AttackUnit  {
    public Cannon(int armor, int baseDamage){
    
        super.armor = ARMOR_CANNON + (PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * armor) * 1000/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_CANNON + (PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * baseDamage) * 1000/100;
        super.experience = 0;
        super.sanctified = false;
    }

    public Cannon() {
        super.armor = ARMOR_CANNON;
        super.initialArmor = ARMOR_CANNON;
        super.baseDamage =  BASE_DAMAGE_CANNON;
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
        return FOOD_COST_CANNON;
    }

    public int getWoodCost(){
        return WOOD_COST_CANNON;
    }

    public int getIronCost(){
        return IRON_COST_CANNON;
    }

    public int getManaCost(){
        return MANA_COST_CANNON;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_CANNON;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_CANNON;
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


