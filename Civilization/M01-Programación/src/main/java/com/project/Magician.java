package com.project;

public class Magician extends SpecialUnit {

    public Magician(int armor, int baseDamage){
        super.armor = 0;
        super.initialArmor = 0;
        super.baseDamage = BASE_DAMAGE_MAGICIAN + (PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY * baseDamage) * BASE_DAMAGE_MAGICIAN/100;
        super.experience = 0;
    }

    public void sanctify(){
        
    }

    public void setBaseDamage(int damage){
        this.baseDamage = damage;
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
        return FOOD_COST_MAGICIAN;
    }

    public int getWoodCost(){
        return WOOD_COST_MAGICIAN;
    }

    public int getIronCost(){
        return IRON_COST_MAGICIAN;
    }

    public int getManaCost(){
        return MANA_COST_MAGICIAN;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_MAGICIAN;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_MAGICIAN;
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