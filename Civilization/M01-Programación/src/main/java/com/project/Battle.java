package com.project;

import java.util.ArrayList;


public class Battle {
    ArrayList<MilitaryUnit> civilizationArmy;
    ArrayList<MilitaryUnit> enemyArmy;
    ArrayList<MilitaryUnit> armies;
    String battleDevelopment;
    int initialCostFleet;
    int initialNumberUnitsCivilization;
    int initialNumberUnitsEnemy;
    int wasteWoodIron;
    int enemyDrops;
    int civilizationDrops;
    int resourcesLooses;
    int initialArmies;
    int[] actualNumberUnitsCivilization;
    int[] actualNumberUnitsEnemy;

    public Battle(ArrayList<MilitaryUnit> army){
        this.civilizationArmy = civilizationArmy;
    }

    String getBattleReport(int battles){
        return "";
    }

    String getBattleDevelopment(){
        return "";
    }

    void initInitialArmies(){

    }

    void updateResourcesLooses(){

    }

    int fleetResourceCost(ArrayList<MilitaryUnit> army) {
        return 0;
    }

    int initialFleetNumber(ArrayList<MilitaryUnit> army){
        return 0;
    }

    int remainderPercentageFleet(ArrayList<MilitaryUnit> army){
        return 0;
    }

    int getGroupDefender(ArrayList<MilitaryUnit> army){
        return 0;
    }

    int getCivilizationGroupAttacker(){
        return 0;
    }
    
    int getEnemyGroupAttacker(){
        return 0;
    }

    void resetArmyArmor(){

    }
}
