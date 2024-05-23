package com.project;

public class Swordsman extends AttackUnit {
    public Swordsman(int armor, int baseDamage){
        super.armor = ARMOR_SWORDSMAN + (PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * armor) * ARMOR_SWORDSMAN/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_SWORDSMAN + (PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * baseDamage) * BASE_DAMAGE_SWORDSMAN/100;
        super.experience = 0;
        super.sanctified = false;
    }

    public Swordsman() {
        super.armor = ARMOR_SWORDSMAN;
        super.initialArmor = ARMOR_SWORDSMAN;
        super.baseDamage =  BASE_DAMAGE_SWORDSMAN;
        super.experience = 0;
        super.sanctified = false;
    }

    public void sanctify(){
        if(super.sanctified == false){
            super.armor += armor*PLUS_ARMOR_UNIT_SANCTIFIED/100;
            super.baseDamage += baseDamage*PLUS_ATTACK_UNIT_SANCTIFIED/100;
        }
        super.sanctified = true;
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
        return FOOD_COST_SWORDSMAN;
    }

    public int getWoodCost(){
        return WOOD_COST_SWORDSMAN;
    }

    public int getIronCost(){
        return IRON_COST_SWORDSMAN;
    }

    public int getManaCost(){
        return MANA_COST_SWORDSMAN;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_SWORDSMAN;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_SWORDSMAN;
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
