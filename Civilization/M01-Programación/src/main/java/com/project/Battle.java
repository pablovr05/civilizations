package com.project;

import java.util.ArrayList;


public class Battle {
    ArrayList<ArrayList<MilitaryUnit>> civilizationArmy;//
    ArrayList<ArrayList<MilitaryUnit>> enemyArmy;//
    ArrayList<ArrayList<ArrayList<MilitaryUnit>>> armies;//
    String battleDevelopment;//
    ArrayList<int[]> initialCostFleet;//
    int initialNumberUnitsCivilization;//
    int initialNumberUnitsEnemy;//
    int wasteWoodIron;
    int enemyDrops;
    int civilizationDrops;
    int resourcesLooses;
    int initialArmies;
    int[] actualNumberUnitsCivilization;//
    int[] actualNumberUnitsEnemy;//

    public Battle(ArrayList<ArrayList<MilitaryUnit>> civilizationArmy, ArrayList<ArrayList<MilitaryUnit>> enemyArmy ){
        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;
        this.armies = new ArrayList<>();
        this.armies.add(civilizationArmy);
        this.armies.add(enemyArmy);
        this.battleDevelopment = "";
        this.initialCostFleet = getInitialCostFleet();
        this.initialNumberUnitsCivilization = getArmyQuantity(civilizationArmy);
        this.initialNumberUnitsEnemy = getArmyQuantity(enemyArmy);
        this.actualNumberUnitsCivilization = getArrayQuantities(civilizationArmy);
        this.actualNumberUnitsEnemy = getArrayQuantities(enemyArmy);
    }

    public int getArmyQuantity(ArrayList<ArrayList<MilitaryUnit>> army){
        int suma = 0;
        for(int i = 0; i<army.size(); i++){
            suma += army.get(i).size();
        }
        
        return suma;
    }

    public int[] getArrayQuantities(ArrayList<ArrayList<MilitaryUnit>> army){
        int[] array = new int[army.size()];
        for(int i = 0; i<army.size(); i++){
            array[i] = army.get(i).size();
        }
        return array;
    }

    public ArrayList<int[]> getInitialCostFleet(){
        ArrayList<int[]> initialCostFleet = new ArrayList<>(); 
        int[] lista = new int[3];
        int comida = 0;
        int madera = 0;
        int hierro = 0;
        for(int i = 0; i<civilizationArmy.size(); i++){
            comida += civilizationArmy.get(i).size()*Variables.FOOD_COST_UNITS[i];
            madera += civilizationArmy.get(i).size()*Variables.WOOD_COST_UNITS[i];
            hierro += civilizationArmy.get(i).size()*Variables.IRON_COST_UNITS[i];
        }
        lista[0] = comida;
        lista[1] = madera;
        lista[2] = hierro;
        initialCostFleet.add(lista);

        lista = new int[3];
        comida = 0;
        madera = 0;
        hierro = 0;
        for(int i = 0; i<enemyArmy.size(); i++){
            comida += enemyArmy.get(i).size()*Variables.FOOD_COST_UNITS[i];
            madera += enemyArmy.get(i).size()*Variables.WOOD_COST_UNITS[i];
            hierro += enemyArmy.get(i).size()*Variables.IRON_COST_UNITS[i];
        }
        lista[0] = comida;
        lista[1] = madera;
        lista[2] = hierro;
        initialCostFleet.add(lista);
        return initialCostFleet;
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