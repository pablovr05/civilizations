package com.project;

public class Crossbow extends AttackUnit {
    public Crossbow(int armor, int baseDamage){

        double armorPercentage = 1 + (double)armor * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY / 100;
        double damagePercentage = 1 + (double)baseDamage  * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY / 100;
        int adjustedArmor = (int)(armor * armorPercentage);
        int adjustedDamage = (int)(baseDamage * damagePercentage);
        
        super.armor = adjustedArmor;
        super.baseDamage = adjustedDamage;
        super.initialArmor = super.armor;
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
