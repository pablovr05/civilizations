package com.project;

public class RocketLauncherTower extends DefenseUnit {

    public RocketLauncherTower(int armor, int baseDamage){
        super.armor = ARMOR_ROCKETLAUNCHERTOWER + (PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * Civilization.getTechnologyDefense()) * 1000/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * Civilization.getTechnologyAttack()) * 1000/100;
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
        return FOOD_COST_ROCKETLAUNCHERTOWER;
    }

    public int getWoodCost(){
        return WOOD_COST_ROCKETLAUNCHERTOWER;
    }

    public int getIronCost(){
        return IRON_COST_ROCKETLAUNCHERTOWER;
    }

    public int getManaCost(){
        return MANA_COST_ROCKETLAUNCHERTOWER;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
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