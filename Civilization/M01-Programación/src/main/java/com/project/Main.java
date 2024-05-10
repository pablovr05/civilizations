package com.project;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Civilization civilization = new Civilization();
    }

    public static ArrayList<MilitaryUnit> createEnemyArmy(int battles){
        ArrayList<MilitaryUnit> army = new ArrayList<>();
        boolean creado = false;
        int madera = Variables.WOOD_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.WOOD_BASE_ENEMY_ARMY/100);
        int comida = Variables.FOOD_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.FOOD_BASE_ENEMY_ARMY/100);
        int hierro = Variables.IRON_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.IRON_BASE_ENEMY_ARMY/100);

        while(enemyCreationAvailable(madera, comida, hierro)){

        }

        return army;
    }

    private static boolean enemyCreationAvailable(int madera, int comida, int hierro){
        // Comprobamos el Swordsman
        if(madera>=Variables.WOOD_COST_SWORDSMAN && comida >=Variables.FOOD_COST_SWORDSMAN && hierro>=Variables.IRON_COST_SWORDSMAN){
            return true;
        }

        // Comprobamos el Spearsman
        if(madera>=Variables.WOOD_COST_SPEARMAN && comida >=Variables.FOOD_COST_SPEARMAN && hierro>=Variables.IRON_COST_SPEARMAN){
            return true;
        }


        // Comprobamos el Crossbow
        if(madera>=Variables.WOOD_COST_CROSSBOW && comida >=Variables.FOOD_COST_CROSSBOW && hierro>=Variables.IRON_COST_CROSSBOW){
            return true;
        }


        // Comprobamos el Cannon
        if(madera>=Variables.WOOD_COST_CANNON && comida >=Variables.FOOD_COST_CANNON && hierro>=Variables.IRON_COST_CANNON){
            return true;
        }


        return false;
    }
}
