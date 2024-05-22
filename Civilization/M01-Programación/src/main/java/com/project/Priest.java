package com.project;

public class Priest extends SpecialUnit {

    public Priest(int armor, int baseDamage){
        super.armor = 0;
        super.initialArmor = 0;
        super.baseDamage = 0;
        super.experience = 0;
    }

    public void sanctify(){
        
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
        return FOOD_COST_PRIEST;
    }

    public int getWoodCost(){
        return WOOD_COST_PRIEST;
    }

    public int getIronCost(){
        return IRON_COST_PRIEST;
    }

    public int getManaCost(){
        return MANA_COST_PRIEST;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_PRIEST;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_PRIEST;
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