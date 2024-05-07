package com.project;

public class Catapult extends DefenseUnit {

    public Catapult(int armor, int baseDamage){
        super.armor = ARMOR_CATAPULT + (PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * civilization.getTechnologyDefense()) * 1000/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_CATAPULT + (PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * civilization.getTechnologyAttack()) * 1000/100;
        super.experience = 0;
        super.sanctified = false;
    }
}