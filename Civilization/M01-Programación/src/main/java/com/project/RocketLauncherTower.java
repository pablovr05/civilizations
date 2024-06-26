package com.project;

public class RocketLauncherTower extends DefenseUnit {

    public RocketLauncherTower(int armor, int baseDamage){
        super.armor = ARMOR_ROCKETLAUNCHERTOWER + (PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * armor) * ARMOR_ROCKETLAUNCHERTOWER/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * baseDamage) * BASE_DAMAGE_ROCKETLAUNCHERTOWER/100;
        super.experience = 0;
        super.sanctified = false;
    }

    public RocketLauncherTower(int unitId, int armor, int baseDamage, int experience, boolean sanctified) {
        super.armor = armor;
        super.initialArmor = Variables.ARMOR_ROCKETLAUNCHERTOWER;
        super.baseDamage = baseDamage;
        super.experience = experience;
        super.sanctified = sanctified;
    }

    public int getSanctified(){
        if(super.sanctified){
            return 1;
        } else{
            return 0;
        }
    }
    public void setInitialArmor(int initialArmor){
        this.initialArmor = initialArmor;
    }

    public void setBaseDamage(int damage){
        this.baseDamage = damage;
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

    public void printearBonito() {
        String sql = "Type: RocketLauncherTower" + " armor: " + this.armor + " initialArmor: " + this.initialArmor + " baseDamage: " + this.baseDamage + " experience: " + this.experience + " sanctified: " + this.sanctified;
        System.out.println(sql);
    }
}