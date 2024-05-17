package com.project;

import java.util.ArrayList;

import oracle.net.aso.c;


public class Battle {
    ArrayList<ArrayList<MilitaryUnit>> civilizationArmy;
    ArrayList<ArrayList<MilitaryUnit>> enemyArmy;
    ArrayList<ArrayList<ArrayList<MilitaryUnit>>> armies;
    String battleDevelopment;
    ArrayList<int[]> initialCostFleet;
    int initialNumberUnitsCivilization;
    int initialNumberUnitsEnemy;
    int[] wasteWoodIron = new int[2];
    int[] enemyDrops = new int[4];
    int[] civilizationDrops = new int[9];
    ArrayList<int[]> resourcesLooses;
    ArrayList<int[]> initialArmies = new ArrayList<>();
    int[] actualNumberUnitsCivilization;
    int[] actualNumberUnitsEnemy;

    public Battle(ArrayList<ArrayList<MilitaryUnit>> civilizationArmy, ArrayList<ArrayList<MilitaryUnit>> enemyArmy ){
        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;
        initInitialArmies();
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

    public void printBattle(){
        System.out.println("Civilizaiton Army");
        System.out.println(civilizationArmy);
        System.out.println("Enemy Army");
        System.out.println(enemyArmy);
        System.out.println("Armies");
        System.out.println(armies);
        System.out.println("Battle Development");
        System.out.println(battleDevelopment);
        System.out.println("Initial Cost Fleet");
        System.out.println(initialCostFleet);
        System.out.println("Unit civilization");
        System.out.println(initialNumberUnitsCivilization);
        System.out.println("units enemy");
        System.out.println(initialNumberUnitsEnemy);
        System.out.println("Inital Armies");
        System.out.println(initialArmies);
    }

    String startBattle(){
        while(remainderPercentageFleet(civilizationArmy)>=20 || remainderPercentageFleet(enemyArmy)>=20){
            int attackGroupCivilization = getCivilizationGroupAttacker();
            int attackGroupEnemy = getEnemyGroupAttacker();

            MilitaryUnit attackerCivilization = civilizationArmy.get(attackGroupCivilization).get((int)(Math.random()*(civilizationArmy.get(attackGroupCivilization).size()-1)));
            // System.out.println("1");
            MilitaryUnit defenderEnemy = enemyArmy.get(attackGroupEnemy).get((int)(Math.random()*(enemyArmy.get(attackGroupEnemy).size()-1)));
            // System.out.println("2");

            this.battleDevelopment+="********************CHANGE ATTACKER********************\n";
            this.battleDevelopment+= "Attacks Civilization: "+attackerCivilization.getClass().getName()+" attacks "+defenderEnemy.getClass().getName()+"\n";
            int attackCivilization = attackerCivilization.attack();
            this.battleDevelopment+=attackerCivilization.getClass().getName()+" generates the damage = "+attackCivilization+"\n";
            defenderEnemy.takeDamage(attackCivilization);
            int defenseEnemy = defenderEnemy.getActualArmor();
            this.battleDevelopment+=defenderEnemy.getClass().getName()+" stays with armor = " + defenseEnemy+"\n";
            if(defenseEnemy<=0){
                this.battleDevelopment+="We eliminate "+defenderEnemy+"\n";
                generateWaste(defenderEnemy);
                addDropUnit(defenderEnemy, true);
                enemyArmy.get(attackGroupEnemy).remove(defenderEnemy);
                // System.out.println("3");
                attackGroupEnemy = getEnemyGroupAttacker();
                defenderEnemy = enemyArmy.get(attackGroupEnemy).get((int)(Math.random()*(enemyArmy.get(attackGroupEnemy).size()-1)));
                // System.out.println("4");
            }
            int random = (int)(Math.random()*100);
            if(random<=attackerCivilization.getChanceAttackAgain()){
                this.battleDevelopment+= "Attacks Civilization: "+attackerCivilization.getClass().getName()+" attacks "+defenderEnemy.getClass().getName()+"\n";
                attackCivilization = attackerCivilization.attack();
                this.battleDevelopment+=attackerCivilization.getClass().getName()+" generates the damage = "+attackCivilization+"\n";
                defenderEnemy.takeDamage(attackCivilization);
                defenseEnemy = defenderEnemy.getActualArmor();
                this.battleDevelopment+=defenderEnemy.getClass().getName()+" stays with armor = " + defenseEnemy+"\n";
                if(defenseEnemy<=0){
                    this.battleDevelopment+="We eliminate "+defenderEnemy+"\n";
                    generateWaste(defenderEnemy);
                    addDropUnit(defenderEnemy, true);
                    enemyArmy.get(attackGroupEnemy).remove(defenderEnemy);
                    // System.out.println("5");
                }
            }

            attackGroupEnemy = getEnemyGroupAttacker();
            int defenseGroup = getGroupDefender(civilizationArmy);

            MilitaryUnit defenderCivilization = civilizationArmy.get(defenseGroup).get((int)(Math.random()*(civilizationArmy.get(attackGroupCivilization).size()-1)));
            // System.out.println("6");
            MilitaryUnit attackerEnemy = enemyArmy.get(attackGroupEnemy).get((int)(Math.random()*(enemyArmy.get(attackGroupEnemy).size()-1)));
            // System.out.println("7");

            this.battleDevelopment+="********************CHANGE ATTACKER********************\n";
            this.battleDevelopment+= "Attacks enemy army: "+attackerEnemy.getClass().getName()+" attacks "+defenderCivilization.getClass().getName()+"\n";
            int attackEnemy = attackerEnemy.attack();
            this.battleDevelopment+=attackerEnemy.getClass().getName()+" generates the damage = "+attackEnemy+"\n";
            defenderCivilization.takeDamage(attackEnemy);
            int defenseCivilization = defenderCivilization.getActualArmor();
            this.battleDevelopment+=defenderCivilization.getClass().getName()+" stays with armor = " + defenseCivilization+"\n";
            if(defenseCivilization<=0){
                this.battleDevelopment+="We lose "+defenderCivilization+"\n";
                generateWaste(defenderCivilization);
                addDropUnit(defenderCivilization, false);
                civilizationArmy.get(attackGroupCivilization).remove(defenderCivilization);
                // System.out.println("8");
                defenseGroup = getGroupDefender(civilizationArmy);
                defenderCivilization = civilizationArmy.get(defenseGroup).get((int)(Math.random()*(civilizationArmy.get(defenseGroup).size()-1)));
                // System.out.println("9");
            }
            random = (int)(Math.random()*100);
            if(random<=attackerEnemy.getChanceAttackAgain()){
                this.battleDevelopment+= "Attacks enemy army: "+attackerEnemy.getClass().getName()+" attacks "+defenderCivilization.getClass().getName()+"\n";
                attackEnemy = attackerEnemy.attack();
                this.battleDevelopment+=attackerEnemy.getClass().getName()+" generates the damage = "+attackEnemy+"\n";
                defenderCivilization.takeDamage(attackEnemy);
                defenseCivilization = defenderCivilization.getActualArmor();
                this.battleDevelopment+=defenderCivilization.getClass().getName()+" stays with armor = " + defenseCivilization+"\n";
                if(defenseEnemy<=0){
                    this.battleDevelopment+="We lose "+defenderCivilization+"\n";
                    generateWaste(defenderCivilization);
                    addDropUnit(defenderCivilization, false);
                    civilizationArmy.get(attackGroupCivilization).remove(defenderCivilization);
                }
            }
        }

        if(remainderPercentageFleet(enemyArmy)<=20){
            return "civilization";
        } else {
            return "enemy";
        }
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

        for(int i = 0; i<4; i++){
            total += actualNumberUnitsCivilization[i];
        }

        int swordsman = 100*actualNumberUnitsCivilization[0]/total;
        int spearman = swordsman + 100*actualNumberUnitsCivilization[1]/total;
        int crossbow = spearman + 100*actualNumberUnitsCivilization[2]/total;

        while(true){
            int random = (int)(Math.random()*100);
            if(random<=swordsman){
                int num = 0;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            } else if(random<=spearman){
                int num = 1;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            } else if(random<=crossbow){
                int num = 2;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            } else{
                int num = 3;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            }
        }
    }

    int getCivilizationGroupAttacker(){
        while(true){
            int random = (int)(Math.random()*100);
            if(random<=4){
                int num = 0;
                if(!civilizationArmy.get(num).isEmpty()){
                    return num;
                }
            } else if(random<=13){
                int num = 1;
                if(!civilizationArmy.get(num).isEmpty())
                {return num;}
            } else if(random<=26){
                int num = 2;
                if(!civilizationArmy.get(num).isEmpty())
                {return num;}
            } else if(random<=63){
                int num = 3;
                if(!civilizationArmy.get(num).isEmpty())
                {return num;}
            } else if(random<=67){
                int num = 4;
                if(!civilizationArmy.get(num).isEmpty())
                {return num;}
            } else if(random<=76){
                int num = 5;
                if(!civilizationArmy.get(num).isEmpty())
                {return num;}
            } else if(random<=90){
                int num = 6;
                if(!civilizationArmy.get(num).isEmpty())
                {return num;}
            } else{
                int num = 7;
                if(!civilizationArmy.get(num).isEmpty())
                {return num;}
            }
        }
            
    }
    
    int getEnemyGroupAttacker(){
        while(true){
            int random = (int)(Math.random()*100);
            if(random<=10){
                int num = 0;
                if(!enemyArmy.get(num).isEmpty())
                {return num;}
            } else if(random<=30){
                int num = 1;
                if(!enemyArmy.get(num).isEmpty())
                {return num;}
            } else if(random<=60){
                int num = 2;
                if(!enemyArmy.get(num).isEmpty())
                {return num;}
            } else {
                int num = 3;
                if(!enemyArmy.get(num).isEmpty())
                {return num;}
            }
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

    void addDropUnit(MilitaryUnit unit, boolean enemy){
        Class clase = unit.getClass();
        switch (clase.getName()){
            case("Swordsman"):
                if(enemy){
                    enemyDrops[0] += 1;
                }else{
                    civilizationDrops[0] += 1;
                } break;
            case("Spearman"):
                if(enemy){
                    enemyDrops[1] += 1;
                }else{
                    civilizationDrops[1] += 1;
                } break;
            case("Crossbow"):
                if(enemy){
                    enemyDrops[2] += 1;
                }else{
                    civilizationDrops[2] += 1;
                } break;
            case("Cannon"):
                if(enemy){
                    enemyDrops[3] += 1;
                }else{
                    civilizationDrops[3] += 1;
                } break;
            case("ArrowTower"):
                civilizationDrops[4] += 1;
                break;
            case("Catapult"):
                civilizationDrops[5] += 1;
                break;
            case("RocketLauncherTower"):
                civilizationDrops[6] += 1;
                break;
            case("Magician"):
                civilizationDrops[7] += 1;
                break;
            case("Priest"):
                civilizationDrops[8] += 1;
                break;
        }
    }
}