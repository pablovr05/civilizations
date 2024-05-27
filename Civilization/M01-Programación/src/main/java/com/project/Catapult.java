package com.project;

public class Catapult extends DefenseUnit {

    public Catapult(int armor, int baseDamage){
        super.armor = ARMOR_CATAPULT + (PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * armor) * ARMOR_CATAPULT/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_CATAPULT + (PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * baseDamage) * ARMOR_CATAPULT/100;
        super.experience = 0;
        super.sanctified = false;
    }

    public Catapult(int unitId, int armor, int baseDamage, int experience, boolean sanctified) {
        super.armor = armor;
        super.initialArmor = Variables.ARMOR_CATAPULT;
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

    public void sanctify(){
        if(super.sanctified == false){
            super.armor += armor*PLUS_ARMOR_UNIT_SANCTIFIED/100;
            super.baseDamage += baseDamage*PLUS_ATTACK_UNIT_SANCTIFIED/100;
        }
        super.sanctified = true;
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
        return FOOD_COST_CATAPULT;
    }

    public int getWoodCost(){
        return WOOD_COST_CATAPULT;
    }

    public int getIronCost(){
        return IRON_COST_CATAPULT;
    }

    public int getManaCost(){
        return MANA_COST_CATAPULT;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_CATAPULT;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_CATAPULT;
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
        String sql = "Type: Catapult" + " armor: " + this.armor + " initialArmor: " + this.initialArmor + " baseDamage: " + this.baseDamage + " experience: " + this.experience + " sancitfied: " + this.sanctified;
        System.out.println(sql);
    }
}