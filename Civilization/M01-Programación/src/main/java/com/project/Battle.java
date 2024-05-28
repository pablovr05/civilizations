package com.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Battle {

    String winner;
    ArrayList<ArrayList<MilitaryUnit>> civilizationArmy;
    ArrayList<ArrayList<MilitaryUnit>> enemyArmy;
    ArrayList<ArrayList<ArrayList<MilitaryUnit>>> armies;
    String battleDevelopment;
    ArrayList<ArrayList<String[]>> desarrolloBatalla;
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

    public Battle(ArrayList<ArrayList<MilitaryUnit>> civilizationArmy, ArrayList<ArrayList<MilitaryUnit>> enemyArmy){
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
        System.out.println(remainderPercentageFleet(civilizationArmy));
        System.out.println(remainderPercentageFleet(enemyArmy));
    }

/*
lista -> [[["atacante_civilization", "defensor_enemy", "ataque", "defensa", "repetir"],["atacante_enemy", "defensor_civilization", ataque, defensa, repetir]], ...]

La lista grande es todo el log, cada lista dentro de esta es un turno, y cada turno tiene una lista que es el ataque de la civilizacion y el ataque del enemigo.

Cada ataque se compone de 5 Strings: El nombre del atacante, el nombre del defensor, el numero de ataque, el número de defensa y el boleano de si se repite el ataque.
*/



    String startBattle(Civilization civilization) {

        System.out.println(initialCostFleet);

        this.desarrolloBatalla = new ArrayList<>();
        while (remainderPercentageFleet(civilizationArmy) >= 20 && remainderPercentageFleet(enemyArmy) >= 20) {
            int attackGroupCivilization = getCivilizationGroupAttacker();
            int attackGroupEnemy = getEnemyGroupAttacker();
            // Verifica que los grupos no estén vacíos
            if (civilizationArmy.get(attackGroupCivilization).isEmpty() || enemyArmy.get(attackGroupEnemy).isEmpty()) {
                continue;
            }
            
            // Selecciona unidades atacante y defensora
            MilitaryUnit attackerCivilization = civilizationArmy.get(attackGroupCivilization)
                    .get((int) (Math.random() * civilizationArmy.get(attackGroupCivilization).size()));
            MilitaryUnit defenderEnemy = enemyArmy.get(attackGroupEnemy)
                    .get((int) (Math.random() * enemyArmy.get(attackGroupEnemy).size()));

            // Ataca la unidad enemiga
            this.battleDevelopment += "********************CHANGE ATTACKER********************\n";
            String[] turnoCivilizacion = new String[5];
            this.battleDevelopment += "Attacks Civilization: " + getClassName(attackerCivilization) + " attacks " + getClassName(defenderEnemy) + "\n";
            turnoCivilizacion[0] = getClassName(attackerCivilization);
            turnoCivilizacion[1] = getClassName(defenderEnemy);
            int attackCivilization = attackerCivilization.attack();
            turnoCivilizacion[2] = String.valueOf(attackCivilization);
            this.battleDevelopment += getClassName(attackerCivilization) + " generates the damage = " + attackCivilization + "\n";
            defenderEnemy.takeDamage(attackCivilization);
            int defenseEnemy = defenderEnemy.getActualArmor();
            turnoCivilizacion[3] = String.valueOf(defenseEnemy);
            this.battleDevelopment += getClassName(defenderEnemy) + " stays with armor = " + defenseEnemy + "\n";

            turnoCivilizacion[4] = "0";
            if (defenseEnemy <= 0) {
                this.battleDevelopment += "We eliminate " + getClassName(defenderEnemy) + "\n";
                generateWaste(defenderEnemy);
                addDropUnit(defenderEnemy, true);
                enemyArmy.get(attackGroupEnemy).remove(defenderEnemy);
            } else{

                // Segunda posibilidad de ataque
                int random = (int) (Math.random() * 100);

                if (random <= attackerCivilization.getChanceAttackAgain() && !enemyArmy.get(attackGroupEnemy).isEmpty()) {
                    // defenderEnemy = enemyArmy.get(attackGroupEnemy)
                    //         .get((int) (Math.random() * enemyArmy.get(attackGroupEnemy).size()));
                    turnoCivilizacion[4] = "1";
                    this.battleDevelopment += "Attacks Civilization: " + getClassName(attackerCivilization) + " attacks " + getClassName(defenderEnemy) + "\n";
                    attackCivilization = attackerCivilization.attack();
                    this.battleDevelopment += getClassName(attackerCivilization) + " generates the damage = " + attackCivilization + "\n";
                    defenderEnemy.takeDamage(attackCivilization);
                    defenseEnemy = defenderEnemy.getActualArmor();
                    this.battleDevelopment += getClassName(defenderEnemy) + " stays with armor = " + defenseEnemy + "\n";
                    if (defenseEnemy <= 0) {
                        this.battleDevelopment += "We eliminate " + getClassName(defenderEnemy) + "\n";
                        generateWaste(defenderEnemy);
                        addDropUnit(defenderEnemy, true);
                        enemyArmy.get(attackGroupEnemy).remove(defenderEnemy);
                        
                    } else {
                    } 
                } else {
                }
            }
   
            this.actualNumberUnitsCivilization = getArrayQuantities(civilizationArmy);
            this.actualNumberUnitsEnemy = getArrayQuantities(enemyArmy);
            
            // Selecciona defensor y atacante del enemigo
            attackGroupEnemy = getEnemyGroupAttacker();
            int defenseGroup = getGroupDefender(civilizationArmy);
            
            if (civilizationArmy.get(defenseGroup).isEmpty()) {
                continue;  // Evita seleccionar un defensor de un grupo vacío
            }
            
            MilitaryUnit defenderCivilization = civilizationArmy.get(defenseGroup)
                    .get((int) (Math.random() * civilizationArmy.get(defenseGroup).size()));
            MilitaryUnit attackerEnemy = enemyArmy.get(attackGroupEnemy)
                    .get((int) (Math.random() * enemyArmy.get(attackGroupEnemy).size()));
            
            this.battleDevelopment += "********************CHANGE ATTACKER********************\n";
            String[] turnoEnemigo = new String[5];
            this.battleDevelopment += "Attacks enemy army: " + getClassName(attackerEnemy) + " attacks " + getClassName(defenderCivilization) + "\n";

            turnoEnemigo[0] = getClassName(attackerEnemy);
            turnoEnemigo[1] = getClassName(defenderCivilization);
            int attackEnemy = attackerEnemy.attack();
            this.battleDevelopment += getClassName(attackerEnemy) + " generates the damage = " + attackEnemy + "\n";
            turnoEnemigo[2] = String.valueOf(attackEnemy);
            defenderCivilization.takeDamage(attackEnemy);
            int defenseCivilization = defenderCivilization.getActualArmor();
            turnoEnemigo[3] = String.valueOf(defenseCivilization);
            this.battleDevelopment += getClassName(defenderCivilization) + " stays with armor = " + defenseCivilization + "\n";
            turnoEnemigo[4] = "0";

            if (defenseCivilization <= 0) {
                this.battleDevelopment += "We lose " + getClassName(defenderCivilization) + "\n";
                generateWaste(defenderCivilization);
                addDropUnit(defenderCivilization, false);
                civilizationArmy.get(defenseGroup).remove(defenderCivilization);
                civilization.army.get(defenseGroup).remove(defenderCivilization);
            }else{
                // Segunda posibilidad de ataque del enemigo
                int random = (int) (Math.random() * 100);
                if (random <= attackerEnemy.getChanceAttackAgain() && !civilizationArmy.get(defenseGroup).isEmpty()) {
                    // defenderCivilization = civilizationArmy.get(defenseGroup)
                    //         .get((int) (Math.random() * civilizationArmy.get(defenseGroup).size()));
                    turnoEnemigo[4] = "1";
                    this.battleDevelopment += "Attacks enemy army: " + getClassName(attackerEnemy) + " attacks " + getClassName(defenderCivilization) + "\n";
                    attackEnemy = attackerEnemy.attack();
                    this.battleDevelopment += getClassName(attackerEnemy) + " generates the damage = " + attackEnemy + "\n";
                    defenderCivilization.takeDamage(attackEnemy);
                    defenseCivilization = defenderCivilization.getActualArmor();
                    this.battleDevelopment += getClassName(defenderCivilization) + " stays with armor = " + defenseCivilization + "\n";
                    if (defenseCivilization <= 0) {
                        this.battleDevelopment += "We lose " + getClassName(defenderCivilization) + "\n";
                        generateWaste(defenderCivilization);
                        addDropUnit(defenderCivilization, false);
                        civilizationArmy.get(defenseGroup).remove(defenderCivilization);
                        civilization.army.get(defenseGroup).remove(defenderCivilization);
                    }
                }
            }

            ArrayList<String[]> turnoCompleto = new ArrayList<>();
            turnoCompleto.add(turnoCivilizacion);
            turnoCompleto.add(turnoEnemigo);
            this.desarrolloBatalla.add(turnoCompleto);
            
            this.actualNumberUnitsCivilization = getArrayQuantities(civilizationArmy);
            this.actualNumberUnitsEnemy = getArrayQuantities(enemyArmy);

        }

        updateResourcesLooses();

        Civilization.gainExperience();

        if (remainderPercentageFleet(civilizationArmy) >= 20) {
            this.winner = "civilization";
        } else {
            this.winner = "enemy";
        }
        return this.winner;
    }
    
    int getEnemyGroupAttacker() {
        while (true) {
            int random = (int) (Math.random() * 100);
            if (random <= 10 && !enemyArmy.get(0).isEmpty()) {
                return 0;
            } else if (random <= 30 && !enemyArmy.get(1).isEmpty()) {
                return 1;
            } else if (random <= 60 && !enemyArmy.get(2).isEmpty()) {
                return 2;
            } else if (!enemyArmy.get(3).isEmpty()) {
                return 3;
            }
        }
    }
    
    public int getCivilizationGroupAttacker() {
        while (true) {
            int random = (int) (Math.random() * 100);
            if (random <= 4 && !civilizationArmy.get(0).isEmpty()) {
                return 0;
            } else if (random <= 16 && !civilizationArmy.get(1).isEmpty()) {
                return 1;
            } else if (random <= 29 && !civilizationArmy.get(2).isEmpty()) {
                return 2;
            } else if (random <= 47 && !civilizationArmy.get(3).isEmpty()) {
                return 3;
            } else if (random <= 68 && !civilizationArmy.get(4).isEmpty()) {
                return 4;
            } else if (random <= 72 && !civilizationArmy.get(5).isEmpty()) {
                return 5;
            } else if (random <= 86 && !civilizationArmy.get(6).isEmpty()) {
                return 6;
            } else if (random <= 100 && !civilizationArmy.get(7).isEmpty()) {
                return 7;
            } 
        }
    }
    
    public int getArmyQuantity(ArrayList<ArrayList<MilitaryUnit>> army){
        int suma = 0;
        for(int i = 0; i<army.size(); i++){
            suma += army.get(i).size();
        }
        
        return suma;
    }

    public static int[] getArrayQuantities(ArrayList<ArrayList<MilitaryUnit>> army){
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
        return initialCostFleetArray;
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
        this.resourcesLooses = new ArrayList<>();
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

    int remainderPercentageFleet(ArrayList<ArrayList<MilitaryUnit>> army) {
        int remainingArmy = getArmyQuantity(army);
        if (this.initialNumberUnitsCivilization != 0 && this.initialNumberUnitsEnemy != 0) {
            if (army == this.civilizationArmy) {
                return (int) (((double) remainingArmy / this.initialNumberUnitsCivilization) * 100);
            } else {
                return (int) (((double) remainingArmy / this.initialNumberUnitsEnemy) * 100);
            }
        }
        return 0;
    }
    

    public int getGroupDefender(ArrayList<ArrayList<MilitaryUnit>> army){
        int total = 0;

        for(int i = 0; i<9; i++){
            total += actualNumberUnitsCivilization[i];
        }

        int swordsman = 100*actualNumberUnitsCivilization[0]/total;
        int spearman = swordsman + 100*actualNumberUnitsCivilization[1]/total;
        int crossbow = spearman + 100*actualNumberUnitsCivilization[2]/total;
        int cannon = crossbow + 100*actualNumberUnitsCivilization[3]/total;
        int arrow_tower = cannon + 100*actualNumberUnitsCivilization[4]/total;
        int catapult = arrow_tower + 100*actualNumberUnitsCivilization[5]/total;
        int rocketlaunchertower = catapult + 100*actualNumberUnitsCivilization[6]/total;
        int magician = rocketlaunchertower + 100*actualNumberUnitsCivilization[7]/total;
        int priest = magician + 100*actualNumberUnitsCivilization[8]/total;

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
            } else if(random<=cannon){
                int num = 3;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            }if(random<=arrow_tower){
                int num = 4;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            } else if(random<=catapult){
                int num = 5;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            } else if(random<=rocketlaunchertower){
                int num = 6;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            } else if(random<=magician){
                int num = 7;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
            } else {
                int num = 8;
                    if(!army.get(num).isEmpty()){
                        return num;
                    }
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
        String clase = getClassName(unit);
        int random = (int)(Math.random()*100);
        switch (clase){
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

    public String getClassName(MilitaryUnit unit){
        Class clase = unit.getClass();
        String name = clase.getName();
        if(name.contains("Swordsman")){
            return "Swordsman";
        } else if(name.contains("Spearman")){
            return "Spearman";
        } else if(name.contains("Crossbow")){
            return "Crossbow";
        } else if(name.contains("Cannon")){
            return "Cannon";
        } else if(name.contains("ArrowTower")){
            return "ArrowTower";
        } else if(name.contains("Catapult")){
            return "Catapult";
        } else if(name.contains("Magician")){
            return "Magician";
        } else if(name.contains("RocketLauncherTower")){
            return "RocketLauncherTower";
        }else {
            return "";
        }
    }
}