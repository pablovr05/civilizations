package com.project;

import java.util.ArrayList;

public class Main {
    public static ArrayList<ArrayList<MilitaryUnit>> enemyArmy;

    public static void main(String[] args) {
        
    }

    public static ArrayList<ArrayList<MilitaryUnit>> createEnemyArmy(int battles){
        ArrayList<ArrayList<MilitaryUnit>> enemyArmy = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            enemyArmy.add(new ArrayList<>());
        }
        int madera = Variables.WOOD_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.WOOD_BASE_ENEMY_ARMY/100);
        int comida = Variables.FOOD_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.FOOD_BASE_ENEMY_ARMY/100);
        int hierro = Variables.IRON_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.IRON_BASE_ENEMY_ARMY/100);

        while(madera>=Variables.WOOD_COST_SWORDSMAN && comida >=Variables.FOOD_COST_SWORDSMAN && hierro>=Variables.IRON_COST_SWORDSMAN){
            int prob = enemyCreationAvailable(madera, comida, hierro); 
            int chance = (int) (Math.random()*prob);
            System.out.println(chance);
            if(chance<= 35){
                enemyArmy.get(0).add(new Swordsman());
                madera -= Variables.WOOD_COST_SWORDSMAN;
                comida -= Variables.FOOD_COST_SWORDSMAN;
                hierro -= Variables.IRON_COST_SWORDSMAN;
            } else if(chance<=60){
                enemyArmy.get(1).add(new Spearman());
                madera -= Variables.WOOD_COST_SPEARMAN;
                comida -= Variables.FOOD_COST_SPEARMAN;
                hierro -= Variables.IRON_COST_SPEARMAN;
            } else if(chance<=80){
                enemyArmy.get(2).add(new Crossbow());
                madera -= Variables.WOOD_COST_CROSSBOW;
                comida -= Variables.FOOD_COST_CROSSBOW;
                hierro -= Variables.IRON_COST_CROSSBOW;
            } else if(chance<=100){
                enemyArmy.get(3).add(new Cannon());
                madera -= Variables.WOOD_COST_CANNON;
                comida -= Variables.FOOD_COST_CANNON;
                hierro -= Variables.IRON_COST_CANNON;
            }
        }

        return enemyArmy;
    }

    private static int enemyCreationAvailable(int madera, int comida, int hierro){
        if(madera>=Variables.WOOD_COST_CANNON && comida >=Variables.FOOD_COST_CANNON && hierro>=Variables.IRON_COST_CANNON){
            return 100;
        } else if(madera>=Variables.WOOD_COST_CROSSBOW && comida >=Variables.FOOD_COST_CROSSBOW && hierro>=Variables.IRON_COST_CROSSBOW){
            return 80;
        } else if(madera>=Variables.WOOD_COST_SPEARMAN && comida >=Variables.FOOD_COST_SPEARMAN && hierro>=Variables.IRON_COST_SPEARMAN){
            return 60;
        } else if(madera>=Variables.WOOD_COST_SWORDSMAN && comida >=Variables.FOOD_COST_SWORDSMAN && hierro>=Variables.IRON_COST_SWORDSMAN){
            return 35;
        } else {
            return 0;
        }
    }

    private static void viewThreat(){
        String swordsman = String.format("%-" + 20 + "s", "Swordsman");
        String spearman = String.format("%-" + 20 + "s", "Spearman");
        String crossbow = String.format("%-" + 20 + "s", "Crossbow");
        String cannon = String.format("%-" + 20 + "s", "Cannon");

        System.out.println("NEW threat INCOMING\n");
        System.out.println(swordsman+enemyArmy.get(0).size());
        System.out.println(spearman+enemyArmy.get(1).size());
        System.out.println(crossbow+enemyArmy.get(2).size());
        System.out.println(cannon+enemyArmy.get(3).size());
    }
}
