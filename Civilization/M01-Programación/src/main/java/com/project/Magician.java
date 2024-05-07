package com.project;

public class Magician extends SpecialUnit {

    public Magician(int armor, int baseDamage){
        super.armor = 0;
        super.initialArmor = 0;
        super.baseDamage = BASE_DAMAGE_MAGICIAN + (PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY * civilization.getTechnologyAttack()) * 1000/100;
        super.experience = 0;
    }
}