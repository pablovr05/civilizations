package com.project;

public class RocketLauncherTower extends DefenseUnit {

    public RocketLauncherTower(int armor, int baseDamage){
        super.armor = ARMOR_ROCKETLAUNCHERTOWER + (PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * civilization.getTechnologyDefense()) * 1000/100;
        super.initialArmor = super.armor;
        super.baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * civilization.getTechnologyAttack()) * 1000/100;
        super.experience = 0;
        super.sanctified = false;
    }
}