package com.project;

public class ArrowTower extends DefenseUnit {

    public ArrowTower(int armor, int baseDamage){
        super.armor = ARMOR_ARROWTOWER + (PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY * civilization.getTechnologyDefense()) * 1000/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_ARROWTOWER + (PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY * civilization.getTechnologyAttack()) * 1000/100;
        super.experience = 0;
        super.sanctified = false;
    }
}