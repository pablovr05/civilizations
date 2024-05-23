package com.project;

public class Crossbow extends AttackUnit {
    public Crossbow(int armor, int baseDamage){

        super.armor = ARMOR_CROSSBOW + (PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * armor) * ARMOR_CROSSBOW/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_CROSSBOW + (PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * baseDamage) * BASE_DAMAGE_CROSSBOW/100;
        super.experience = 0;
        super.sanctified = false;
    }

    public Crossbow() {
        super.armor = ARMOR_CROSSBOW;
        super.initialArmor = ARMOR_CROSSBOW;
        super.baseDamage =  BASE_DAMAGE_CROSSBOW;
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
        return FOOD_COST_CROSSBOW;
    }

    public int getWoodCost(){
        return WOOD_COST_CROSSBOW;
    }

    public int getIronCost(){
        return IRON_COST_CROSSBOW;
    }

    public int getManaCost(){
        return MANA_COST_CROSSBOW;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_CROSSBOW;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_CROSSBOW;
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
