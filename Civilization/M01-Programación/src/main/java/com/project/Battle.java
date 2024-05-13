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
    int[] wasteWoodIron = new int[2];
    int[] enemyDrops = new int[4];
    int[] civilizationDrops = new int[9];
    ArrayList<int[]> resourcesLooses;//
    ArrayList<int[]> initialArmies;//
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
        initInitialArmies();
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
        ArrayList<int[]> initialCostFleetArray = new ArrayList<>(); 
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
        initialCostFleetArray.add(lista);

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
        initialCostFleetArray.add(lista);
        return initialCostFleet;
    }

    String getBattleReport(int battles){
        return "";
    }

    String getBattleDevelopment(){
        return this.battleDevelopment;
    }

    void initInitialArmies(){
        this.initialArmies.add(getArrayQuantities(civilizationArmy));
        this.initialArmies.add(getArrayQuantities(enemyArmy));
    }

    void updateResourcesLooses(){
        resourcesLooses = new ArrayList<>();
        int[] loss = new int[4];
        int madera = 0;
        int comida = 0;
        int hierro = 0;
        for(int i = 0; i<civilizationArmy.size(); i++){
            madera += Variables.WOOD_COST_UNITS[i]*(initialArmies.get(0)[i]-civilizationArmy.get(i).size());
            comida += Variables.FOOD_COST_UNITS[i]*(initialArmies.get(0)[i]-civilizationArmy.get(i).size());
            hierro += Variables.IRON_COST_UNITS[i]*(initialArmies.get(0)[i]-civilizationArmy.get(i).size());
        }
        loss[0] = comida;
        loss[1] = madera;
        loss[2] = hierro;
        loss[3] = comida*10+madera*5+hierro;
        this.resourcesLooses.add(loss);

        loss = new int[4];
        madera = 0;
        comida = 0;
        hierro = 0;
        for(int i = 0; i<enemyArmy.size(); i++){
            madera += Variables.WOOD_COST_UNITS[i]*(initialArmies.get(1)[i]-civilizationArmy.get(i).size());
            comida += Variables.FOOD_COST_UNITS[i]*(initialArmies.get(1)[i]-civilizationArmy.get(i).size());
            hierro += Variables.IRON_COST_UNITS[i]*(initialArmies.get(1)[i]-civilizationArmy.get(i).size());
        }
        loss[0] = comida;
        loss[1] = madera;
        loss[2] = hierro;
        loss[3] = comida*10+madera*5+hierro;
        this.resourcesLooses.add(loss);
    }

    int fleetResourceCost(ArrayList<MilitaryUnit> army) {
        return 0;
    }

    int initialFleetNumber(ArrayList<MilitaryUnit> army){
        return 0;
    }

    int remainderPercentageFleet(ArrayList<ArrayList<MilitaryUnit>> army){
        int remainingArmy = getArmyQuantity(army);
        int size = army.size();
        if(size == 9){
            return initialNumberUnitsCivilization/remainingArmy*100;
        }
        return initialNumberUnitsEnemy/remainingArmy*100;
    }

    int getGroupDefender(ArrayList<ArrayList<MilitaryUnit>> army){
        int total = 0;
        int random = (int)(Math.random()*100);

        for(int i = 0; i<4; i++){
            total += actualNumberUnitsCivilization[i];
        }

        int swordsman = 100*actualNumberUnitsCivilization[0]/total;
        int spearman = swordsman + 100*actualNumberUnitsCivilization[1]/total;
        int crossbow = spearman + 100*actualNumberUnitsCivilization[2]/total;
        if(random<=swordsman){
            return 0;
        } else if(random<=spearman){
            return 1;
        } else if(random<=crossbow){
            return 2;
        } else{
            return 3;
        }
    }

    int getCivilizationGroupAttacker(){
        int random = (int)(Math.random()*100);
        if(random<=4){
            return 0;
        } else if(random<=13){
            return 1;
        } else if(random<=26){
            return 2;
        } else if(random<=63){
            return 3;
        } else if(random<=67){
            return 4;
        } else if(random<=76){
            return 5;
        } else if(random<=90){
            return 6;
        } else{
            return 7;
        }
    }
    
    int getEnemyGroupAttacker(){
        int random = (int)(Math.random()*100);
        if(random<=10){
            return 0;
        } else if(random<=30){
            return 1;
        } else if(random<=60){
            return 2;
        } else {
            return 3;
        }
    }

    void resetArmyArmor(){
        for(int i = 0; i<civilizationArmy.size(); i++){
            for(MilitaryUnit tropa : civilizationArmy.get(i)){
                tropa.resetArmor();
            }
        }
    }

    void generateWaste(MilitaryUnit unit){
        Class clase = unit.getClass();
        int random = (int)(Math.random()*100);
        switch (clase.getName()){
            case("Swordsman"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_SWORDSMAN){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_SWORDSMAN;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_SWORDSMAN;
                } break;
            case("Spearman"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_SPEARMAN){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_SPEARMAN;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_SPEARMAN;
                } break;
            case("Crossbow"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_CROSSBOW){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_CROSSBOW;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_CROSSBOW;
                } break;
            case("Cannon"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_SWORDSMAN){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_SWORDSMAN;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_SWORDSMAN;
                } break;
            case("ArrowTower"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_ARROWTOWER){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_ARROWTOWER;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_ARROWTOWER;
                } break;
            case("Catapult"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_CATAPULT){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_CATAPULT;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_CATAPULT;
                } break;
            case("RocketLauncherTower"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_ROCKETLAUNCHERTOWER;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_ROCKETLAUNCHERTOWER;
                } break;
            case("Magician"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_MAGICIAN){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_MAGICIAN;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_MAGICIAN;
                } break;
            case("Priest"):
                if(random<= Variables.CHANCE_GENERATNG_WASTE_PRIEST){
                    wasteWoodIron[0] += Variables.PERCENTATGE_WASTE*Variables.WOOD_COST_PRIEST;
                    wasteWoodIron[1] += Variables.PERCENTATGE_WASTE*Variables.IRON_COST_PRIEST;
                } break;
        }
    }
}