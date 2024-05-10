package com.project;

public class Swordsman extends AttackUnit {
    public Swordsman(int armor, int baseDamage){

        double armorPercentage = 1 + (double)armor * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY / 100;
        double damagePercentage = 1 + (double)baseDamage  * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY / 100;
        int adjustedArmor = (int)(armor * armorPercentage);
        int adjustedDamage = (int)(baseDamage * damagePercentage);
        
        super.armor = adjustedArmor;
        super.baseDamage = adjustedDamage;
        super.initialArmor = super.armor;
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
