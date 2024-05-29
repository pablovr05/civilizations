package com.project;

import java.util.ArrayList;
import java.util.List;

public class Civilization implements Variables {
    public final int swordsman_index = 0;
    public final int spearman_index = 1;
    public final int crossbow_index = 2;
    public final int cannon_index = 3;
    public final int arrow_tower_index = 4;
    public final int catapult_index = 5;
    public final int rocket_launcher_index = 6;
    public final int magician_index = 7;
    public final int priest_index = 8;


    public int id;
    public String name;
    public int technologyDefense;
    public int technologyAttack;
    public int wood;
    public int iron;
    public int food;
    public int mana;
    public int magicTower;
    public int church;
    public int farm;
    public int smithy;
    public int carpentry;
    public int battles;
    public int foodGeneration;
    public int woodGeneration;
    public int ironGeneration;
    public int manaGeneration;
    public static ArrayList<ArrayList<MilitaryUnit>> army = new ArrayList<>();
    
    public Civilization(){
        // this.id = CivilizationDAO.getLastID();
        this.technologyDefense = 0;
        this.technologyAttack = 0;
        this.wood = 5000;
        this.iron = 5000;
        this.food = 5000;
        this.mana = 0;
        this.magicTower = 0;
        this.church = 0;
        this.farm = 0;
        this.smithy = 0;
        this.carpentry = 0;
        this.battles = 0;
        this.foodGeneration = CIVILIZATION_FOOD_GENERATED;
        this.woodGeneration = CIVILIZATION_WOOD_GENERATED;
        this.ironGeneration = CIVILIZATION_IRON_GENERATED;
        this.manaGeneration = 0;
        army = new ArrayList<>();
        for(int i = 0; i<9; i++){
            army.add(new ArrayList<>());
        }
    }

    public Civilization(String name){
        this.name = name;
        this.technologyDefense = 0;
        this.technologyAttack = 0;
        this.wood = 5000;
        this.iron = 5000;
        this.food = 5000;
        this.mana = 0;
        this.magicTower = 0;
        this.church = 0;
        this.farm = 0;
        this.smithy = 0;
        this.carpentry = 0;
        this.battles = 0;
        this.foodGeneration = CIVILIZATION_FOOD_GENERATED;
        this.woodGeneration = CIVILIZATION_WOOD_GENERATED;
        this.ironGeneration = CIVILIZATION_IRON_GENERATED;
        this.manaGeneration = 0;
        army = new ArrayList<>();
        for(int i = 0; i<9; i++){
            army.add(new ArrayList<>());
        }
    }

    public void sanctifyGroup(int group){
        if(group>=0 && group<=6){
            for(MilitaryUnit unit : army.get(group)){
                unit.sanctify();
            }
        }
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public void setCarpentry(int carpentry) {
        this.carpentry = carpentry;
    }
    
    public void setChurch(int church) {
        this.church = church;
    }
    public void setFarm(int farm) {
        this.farm = farm;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public void setIron(int iron) {
        this.iron = iron;
    }
    public void setMagicTower(int magicTower) {
        this.magicTower = magicTower;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public void setSmithy(int smithy) {
        this.smithy = smithy;
    }
    public void setTechnologyAttack(int technologyAttack) {
        this.technologyAttack = technologyAttack;
    }
    public void setTechnologyDefense(int technologyDefense) {
        this.technologyDefense = technologyDefense;
    }
    public void setWood(int wood) {
        this.wood = wood;
    }
    public int getBattles() {
        return battles;
    }
    public int getCarpentry() {
        return carpentry;
    }
    public int getTechnologyAttack() {
        return technologyAttack;
    }
    public int getTechnologyDefense() {
        return technologyDefense;
    }
    public int getChurch() {
        return church;
    }
    public int getFarm() {
        return farm;
    }
    public int getFood() {
        return food;
    }
    public int getIron() {
        return iron;
    }
    public int getMagicTower() {
        return magicTower;
    }
    public int getMana() {
        return mana;
    }
    public int getSmithy() {
        return smithy;
    }
    public int getWood() {
        return wood;
    }
    
    public ArrayList<ArrayList<MilitaryUnit>> getArmy(){
        return army;
    }

    public int getNombreSwordsman() {
        return getElementCount(swordsman_index);
    }
    
    public int getNombreSpearman() {
        return getElementCount(spearman_index);
    }
    
    public int getNombreCrossbow() {
        return getElementCount(crossbow_index);
    }
    
    public int getNombreCannon() {
        return getElementCount(cannon_index);
    }
    
    public int getNombreArrowTower() {
        return getElementCount(arrow_tower_index);
    }
    
    public int getNombreCatapult() {
        return getElementCount(catapult_index);
    }
    
    public int getNombreRocketLauncher() {
        return getElementCount(rocket_launcher_index);
    }
    
    public int getNombreMagician() {
        return getElementCount(magician_index);
    }
    
    public int getNombrePriest() {
        return getElementCount(priest_index);
    }
    
    public int getElementCount(int index) {
        if (index >= 0 && index < army.size()) {
            List<MilitaryUnit> sublista = army.get(index); // Obtener la sublista de MilitaryUnit
            return sublista.size(); // Devolver el tamaño de la sublista
        } else {
            // Manejo de error si el índice está fuera de rango
            return -1; // o cualquier otro valor que indique un error
        }
    }

    public List<MilitaryUnit> getSublista(int index) {
        if (index >= 0 && index < army.size()) {
            return army.get(index);
        } else {
            // Manejo de error si el índice está fuera de rango
            return null;
        }
    }

    public int[] getUpgradeMaterials() {
        int comida1 = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST + CivilizaciónControlador.civilización.technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST;
        int madera1 = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST + CivilizaciónControlador.civilización.technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST;
        int hierro1 = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST + CivilizaciónControlador.civilización.technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST;
    
        int comida2 = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST + CivilizaciónControlador.civilización.technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST;
        int madera2 = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST + CivilizaciónControlador.civilización.technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST;
        int hierro2 = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST + CivilizaciónControlador.civilización.technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST;
    
        return new int[]{comida1, madera1, hierro1, comida2, madera2, hierro2};
    }    
    
    public void newChurch(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                if (this.food>= FOOD_COST_CHURCH && this.wood >= WOOD_COST_CHURCH && this.iron >= IRON_COST_CHURCH && this.mana >= MANA_COST_CHURCH){
                    this.food -= FOOD_COST_CHURCH;
                    this.wood -= WOOD_COST_CHURCH;
                    this.iron -= IRON_COST_CHURCH;
                    this.mana -= MANA_COST_CHURCH;
                    this.church += 1;
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " Church");
                    throw new ResourceException("Faltan recursos para crear mas Church");
                }
            }
            System.out.println("Se han agregado " + cnt + " Church");
        }
        updateResourceGeneration();
    }

    public void newMagicTower(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                if (this.food>= FOOD_COST_MAGICTOWER && this.wood >= WOOD_COST_MAGICTOWER && this.iron >= IRON_COST_MAGICTOWER){
                    this.food -= FOOD_COST_MAGICTOWER;
                    this.wood -= WOOD_COST_MAGICTOWER;
                    this.iron -= IRON_COST_MAGICTOWER;
                    this.magicTower += 1;
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " Magic Tower");
                    throw new ResourceException("Faltan recursos para crear mas Magic Tower");
                }
            }
            System.out.println("Se han agregado " + cnt + " Magic Tower");
        }
        updateResourceGeneration();
    }
    public void newFarm(int n) throws ResourceException{
        System.out.println(this.food);
        System.out.println(this.wood);
        System.out.println(this.iron);
        System.out.println(n);
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                if (this.food>= FOOD_COST_FARM && this.wood >= WOOD_COST_FARM && this.iron >= IRON_COST_FARM){
                    this.food -= FOOD_COST_FARM;
                    this.wood -= WOOD_COST_FARM;
                    this.iron -= IRON_COST_FARM;
                    this.farm += 1;
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " Farm");
                    throw new ResourceException("Faltan recursos para crear mas Farm");
                }
            }
            System.out.println("Se han agregado " + cnt + " Farm");
        }
        updateResourceGeneration();
    }
    public void newCarpentry(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                if (this.food>= FOOD_COST_CARPENTRY && this.wood >= WOOD_COST_CARPENTRY && this.iron >= IRON_COST_CARPENTRY){
                    this.food -= FOOD_COST_CARPENTRY;
                    this.wood -= WOOD_COST_CARPENTRY;
                    this.iron -= IRON_COST_CARPENTRY;
                    this.carpentry += 1;
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " Carpentry");
                    throw new ResourceException("Faltan recursos para crear mas Carpentry");
                }
            }
            System.out.println("Se han agregado " + cnt + " Carpentry");
        }
        updateResourceGeneration();
    }
    public void newSmithy(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                if (this.food>= FOOD_COST_SMITHY && this.wood >= WOOD_COST_SMITHY && this.iron >= IRON_COST_SMITHY){
                    this.food -= FOOD_COST_SMITHY;
                    this.wood -= WOOD_COST_SMITHY;
                    this.iron -= IRON_COST_SMITHY;
                    this.smithy += 1;
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " Smithy");
                    throw new ResourceException("Faltan recursos para crear mas Smithy");
                }
            }
            System.out.println("Se han agregado " + cnt + " Smithy");
        }
        updateResourceGeneration();
    }

    public String[] newChurchN(int n) {
        int cnt = 0;
        if (n < 1){
            return null;
        } else {
            for(int i=0; i<n; i++) {
                if (this.food >= FOOD_COST_CHURCH && this.wood >= WOOD_COST_CHURCH && this.iron >= IRON_COST_CHURCH && this.mana >= MANA_COST_CHURCH) {
                    this.food -= FOOD_COST_CHURCH;
                    this.wood -= WOOD_COST_CHURCH;
                    this.iron -= IRON_COST_CHURCH;
                    this.mana -= MANA_COST_CHURCH;
                    this.church += 1;
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Church";
                    String message = "Faltan recursos para crear más Church";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Church");
        }
        updateResourceGeneration();
        return new String[]{"Se han agregado " + cnt + " Church", ""};
    }
    
    public String[] newMagicTowerN(int n) {
        int cnt = 0;
        if (n < 1){
            return null;
        } else {
            for(int i=0; i<n; i++) {
                if (this.food >= FOOD_COST_MAGICTOWER && this.wood >= WOOD_COST_MAGICTOWER && this.iron >= IRON_COST_MAGICTOWER) {
                    this.food -= FOOD_COST_MAGICTOWER;
                    this.wood -= WOOD_COST_MAGICTOWER;
                    this.iron -= IRON_COST_MAGICTOWER;
                    this.magicTower += 1;
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Magic Tower";
                    String message = "Faltan recursos para crear más Magic Tower";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Magic Tower");
        }
        updateResourceGeneration();
        return new String[]{"Se han agregado " + cnt + " Magic Tower", ""};
    }
    
    public String[] newFarmN(int n) {
        int cnt = 0;
        if (n < 1){
            return null;
        } else {
            for(int i=0; i<n; i++) {
                if (this.food >= FOOD_COST_FARM && this.wood >= WOOD_COST_FARM && this.iron >= IRON_COST_FARM) {
                    this.food -= FOOD_COST_FARM;
                    this.wood -= WOOD_COST_FARM;
                    this.iron -= IRON_COST_FARM;
                    this.farm += 1;
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Farm";
                    String message = "Faltan recursos para crear más Farm";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Farm");
        }
        updateResourceGeneration();
        return new String[]{"Se han agregado " + cnt + " Farm", ""};
    }
    
    public String[] newCarpentryN(int n) {
        int cnt = 0;
        if (n < 1){
            return null;
        } else {
            for(int i=0; i<n; i++) {
                if (this.food >= FOOD_COST_CARPENTRY && this.wood >= WOOD_COST_CARPENTRY && this.iron >= IRON_COST_CARPENTRY) {
                    this.food -= FOOD_COST_CARPENTRY;
                    this.wood -= WOOD_COST_CARPENTRY;
                    this.iron -= IRON_COST_CARPENTRY;
                    this.carpentry += 1;
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Carpentry";
                    String message = "Faltan recursos para crear más Carpentry";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Carpentry");
        }
        updateResourceGeneration();
        return new String[]{"Se han agregado " + cnt + " Carpentry", ""};
    }

    public String[] newSmithyN(int n) {
        int cnt = 0;
        if (n < 1){
            return null;
        } else {
            for(int i=0; i<n; i++) {
                if (this.food >= FOOD_COST_SMITHY && this.wood >= WOOD_COST_SMITHY && this.iron >= IRON_COST_SMITHY) {
                    this.food -= FOOD_COST_SMITHY;
                    this.wood -= WOOD_COST_SMITHY;
                    this.iron -= IRON_COST_SMITHY;
                    this.smithy += 1;
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Smithy";
                    String message = "Faltan recursos para crear más Smithy";
                    return new String[]{title, message};
                }
            }
        }
        updateResourceGeneration();
        return new String[]{"Se han agregado " + cnt + " Smithy", ""};
    }

    //Comida, Madera, Hierro, Maná, plusFood, plusWoood, PlusHierro, Plus maná
    public int[] getEdificaciónInfo(String tipo) {
        switch (tipo) {
            case "Farm":
                return new int[] {FOOD_COST_FARM, WOOD_COST_FARM, IRON_COST_FARM, 0, CIVILIZATION_FOOD_GENERATED_PER_FARM, 0, 0, 0};
            case "Smithy":
                return new int[] {FOOD_COST_SMITHY, WOOD_COST_SMITHY, IRON_COST_SMITHY, 0, 0, 0, CIVILIZATION_IRON_GENERATED_PER_SMITHY, 0};
            case "Church":
                return new int[] {FOOD_COST_CHURCH, WOOD_COST_CHURCH, IRON_COST_CHURCH, MANA_COST_CHURCH, 0, 0, 0, 0};
            case "Magic Tower":
                return new int[] {FOOD_COST_MAGICTOWER, WOOD_COST_MAGICTOWER, IRON_COST_MAGICTOWER, 0, 0, 0, 0, CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER};
            case "Carpentry":
                return new int[] {FOOD_COST_CARPENTRY, WOOD_COST_CARPENTRY, IRON_COST_CARPENTRY, 0, 0,  CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY, 0, 0};
            default:
                return null;
        }
    }
    //Comida, Madera, Hierro, Maná, Armor, Attack power, Chance attack again, Chance of generate wastings
    public int[] getEjércitoInfo(String tipo) {
        switch (tipo) {
            case "Swordsman":
                return new int[] {FOOD_COST_SWORDSMAN, WOOD_COST_SWORDSMAN, IRON_COST_SWORDSMAN, MANA_COST_SWORDSMAN, ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN, CHANCE_ATTACK_AGAIN_SWORDSMAN, CHANCE_GENERATNG_WASTE_SWORDSMAN};
            case "Spearman":
                return new int[] {FOOD_COST_SPEARMAN, WOOD_COST_SPEARMAN, IRON_COST_SPEARMAN, MANA_COST_SPEARMAN, ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN, CHANCE_ATTACK_AGAIN_SPEARMAN, CHANCE_GENERATNG_WASTE_SPEARMAN};
            case "Crossbow":
                return new int[] {FOOD_COST_CROSSBOW, WOOD_COST_CROSSBOW, IRON_COST_CROSSBOW, MANA_COST_CROSSBOW, ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW, CHANCE_ATTACK_AGAIN_CROSSBOW, CHANCE_GENERATNG_WASTE_CROSSBOW};
            case "Cannon":
                return new int[] {FOOD_COST_CANNON, WOOD_COST_CANNON, IRON_COST_CANNON, MANA_COST_CANNON, ARMOR_CANNON, BASE_DAMAGE_CANNON, CHANCE_ATTACK_AGAIN_CANNON, CHANCE_GENERATNG_WASTE_CANNON};
            case "Arrow Tower":
                return new int[] {FOOD_COST_ARROWTOWER, WOOD_COST_ARROWTOWER, IRON_COST_ARROWTOWER, MANA_COST_ARROWTOWER, ARMOR_ARROWTOWER, BASE_DAMAGE_ARROWTOWER, CHANCE_ATTACK_AGAIN_ARROWTOWER, CHANCE_GENERATNG_WASTE_ARROWTOWER};
            case "Catapult":
                return new int[] {FOOD_COST_CATAPULT, WOOD_COST_CATAPULT, IRON_COST_CATAPULT, MANA_COST_CATAPULT, ARMOR_CATAPULT, BASE_DAMAGE_CATAPULT, CHANCE_ATTACK_AGAIN_CATAPULT, CHANCE_GENERATNG_WASTE_CATAPULT};
            case "Rocket Launcher Tower":
                return new int[] {FOOD_COST_ROCKETLAUNCHERTOWER, WOOD_COST_ROCKETLAUNCHERTOWER, IRON_COST_ROCKETLAUNCHERTOWER, MANA_COST_ROCKETLAUNCHERTOWER, ARMOR_ROCKETLAUNCHERTOWER, BASE_DAMAGE_ROCKETLAUNCHERTOWER, CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER, CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER};
            case "Magician":
                return new int[] {FOOD_COST_MAGICIAN, WOOD_COST_MAGICIAN, IRON_COST_MAGICIAN, MANA_COST_MAGICIAN, 1, BASE_DAMAGE_MAGICIAN, CHANCE_ATTACK_AGAIN_MAGICIAN, CHANCE_GENERATNG_WASTE_MAGICIAN};
            case "Priest":
                return new int[] {FOOD_COST_PRIEST, WOOD_COST_PRIEST, IRON_COST_PRIEST, MANA_COST_PRIEST, 1, 0, CHANCE_ATTACK_AGAIN_PRIEST, CHANCE_GENERATNG_WASTE_PRIEST};
            default:
                return null;
        }
    }
    
    public void upgradeTechnologyDefense() throws ResourceException{
        int comida = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST+this.technologyDefense*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST;
        int madera = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST+this.technologyDefense*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST;
        int hierro = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST+this.technologyDefense*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST;
        if(this.food >= comida && this.wood >= madera && this.iron >= hierro){
            this.food -= comida;
            this.wood -= madera;
            this.iron -= hierro;
            this.technologyDefense += 1;
        }else{
            throw new ResourceException("Faltan recursos para mejorar la TechnologyDefense");  
        }
    }
    public void upgradeTechnologyAttack() throws ResourceException{
        int comida = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST+this.technologyAttack*UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST;
        int madera = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST+this.technologyAttack*UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST;
        int hierro = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST+this.technologyAttack*UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST;
        if(this.food >= comida && this.wood >= madera && this.iron >= hierro){
            this.food -= comida;
            this.wood -= madera;
            this.iron -= hierro;
            this.technologyAttack += 1;
        }else{
            throw new ResourceException("Faltan recursos para mejorar la TechnologyAttack");  
        }
    }

    public void printStats(){
        System.out.println("***************************CIVILIZATION STATS***************************\n");
        System.out.println("---------------------------TECHNOLOGY-----------------------------------");
        System.out.printf("%-10s%-10s%n", "Attack", "Defense");
        System.out.printf("%-10d%-10d%n", this.technologyAttack, this.technologyDefense);
        System.out.println("---------------------------BUILDINGS------------------------------------");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "Farm", "Smithy", "Carpentry", "Magic Tower", "Church");
        System.out.printf("%-15d%-15d%-15d%-15d%-15d%n", this.farm, this.smithy, this.carpentry, this.magicTower, this.church);
        System.out.println("---------------------------DEFENSES-------------------------------------");
        System.out.printf("%-18s%-18s%-18s%n", "Arrow Tower", "Catapult", "Rocket Launcher");
        System.out.printf("%-18d%-18d%-18d%n", this.army.get(arrow_tower_index).size(), this.army.get(catapult_index).size(), this.army.get(rocket_launcher_index).size());
        System.out.println("---------------------------ATTACK UNITS---------------------------------");
        System.out.printf("%-11s%-11s%-11s%-11s%n", "Swordsman", "Spearman", "Crossbow", "Cannon");
        System.out.printf("%-11d%-11d%-11d%-11d%n", this.army.get(swordsman_index).size(), this.army.get(spearman_index).size(), this.army.get(crossbow_index).size(), this.army.get(cannon_index).size());
        System.out.println("---------------------------ESPECIAL UNITS-------------------------------");
        System.out.printf("%-11s%-11s%n", "Magician", "Priest");
        System.out.printf("%-11d%-11d%n", this.army.get(magician_index).size(), this.army.get(priest_index).size());
        System.out.println("---------------------------RESOURCES------------------------------------");
        System.out.printf("%-11s%-11s%-11s%-11s%n", "Food", "Wood", "Iron", "Mana");
        System.out.printf("%-11d%-11d%-11d%-11d%n", this.food, this.wood, this.iron, this.mana);
        System.out.println("---------------------------GENERATION RESOURCES-------------------------");
        System.out.printf("%-11s%-11s%-11s%-11s%n", "Food", "Wood", "Iron", "Mana");
        System.out.printf("%-11d%-11d%-11d%-11d%n", this.foodGeneration, this.woodGeneration, this.ironGeneration, this.manaGeneration);
    }

    public void printArmy(){
        System.out.println("***************************CIVILIZATION ARMY***************************\n");
        System.out.println("---------------------------DEFENSES-------------------------------------");
        System.out.printf("%-18s%-18s%-18s%n", "Arrow Tower", "Catapult", "Rocket Launcher");
        System.out.printf("%-18d%-18d%-18d%n", this.army.get(arrow_tower_index).size(), this.army.get(catapult_index).size(), this.army.get(rocket_launcher_index).size());
        System.out.println("---------------------------ATTACK UNITS---------------------------------");
        System.out.printf("%-11s%-11s%-11s%-11s%n", "Swordsman", "Spearman", "Crossbow", "Cannon");
        System.out.printf("%-11d%-11d%-11d%-11d%n", this.army.get(swordsman_index).size(), this.army.get(spearman_index).size(), this.army.get(crossbow_index).size(), this.army.get(cannon_index).size());
        System.out.println("---------------------------ESPECIAL UNITS-------------------------------");
        System.out.printf("%-11s%-11s%n", "Magician", "Priest");
        System.out.printf("%-11d%-11d%n", this.army.get(magician_index).size(), this.army.get(priest_index).size());
    }

    public String[] newSwordsmanN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                Swordsman Swordsman = new Swordsman(getTechnologyDefense(), getTechnologyAttack());
                if (this.food >= Swordsman.getFoodCost() && this.wood >= Swordsman.getWoodCost() && this.iron >= Swordsman.getIronCost() && this.mana >= Swordsman.getManaCost()) {
                    this.army.get(swordsman_index).add(Swordsman);
                    this.food -= Swordsman.getFoodCost();
                    this.wood -= Swordsman.getWoodCost();
                    this.iron -= Swordsman.getIronCost();
                    this.mana -= Swordsman.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Swordsman";
                    String message = "Faltan recursos para crear más Swordsman";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Swordsman");
        }
        return new String[]{"Se han agregado " + cnt + " Swordsman", ""};
    }
    
    public String[] newCrossbowN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                Crossbow Crossbow = new Crossbow(getTechnologyDefense(), getTechnologyAttack());
                if (this.food >= Crossbow.getFoodCost() && this.wood >= Crossbow.getWoodCost() && this.iron >= Crossbow.getIronCost() && this.mana >= Crossbow.getManaCost()) {
                    this.army.get(crossbow_index).add(Crossbow);
                    this.food -= Crossbow.getFoodCost();
                    this.wood -= Crossbow.getWoodCost();
                    this.iron -= Crossbow.getIronCost();
                    this.mana -= Crossbow.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Crossbows";
                    String message = "Faltan recursos para crear más Crossbows";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Crossbows");
        }
        return new String[]{"Se han agregado " + cnt + " Crossbows", ""};
    }

    public String[] newSpearmanN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                Spearman Spearman = new Spearman(getTechnologyDefense(), getTechnologyAttack());
                if (this.food >= Spearman.getFoodCost() && this.wood >= Spearman.getWoodCost() && this.iron >= Spearman.getIronCost() && this.mana >= Spearman.getManaCost()) {
                    this.army.get(spearman_index).add(Spearman);
                    this.food -= Spearman.getFoodCost();
                    this.wood -= Spearman.getWoodCost();
                    this.iron -= Spearman.getIronCost();
                    this.mana -= Spearman.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Spearmen";
                    String message = "Faltan recursos para crear más Spearmen";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Spearmen");
        }
        return new String[]{"Se han agregado " + cnt + " Spearmen", ""};
    }
    
    public String[] newCannonN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                Cannon Cannon = new Cannon(getTechnologyDefense(), getTechnologyAttack());
                if (this.food >= Cannon.getFoodCost() && this.wood >= Cannon.getWoodCost() && this.iron >= Cannon.getIronCost() && this.mana >= Cannon.getManaCost()) {
                    this.army.get(cannon_index).add(Cannon);
                    this.food -= Cannon.getFoodCost();
                    this.wood -= Cannon.getWoodCost();
                    this.iron -= Cannon.getIronCost();
                    this.mana -= Cannon.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Cannons";
                    String message = "Faltan recursos para crear más Cannons";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Cannons");
        }
        return new String[]{"Se han agregado " + cnt + " Cannons", ""};
    }

    public String[] newArrowTowerN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                ArrowTower arrowTower = new ArrowTower(getTechnologyDefense(), getTechnologyAttack());
                if (this.food >= arrowTower.getFoodCost() && this.wood >= arrowTower.getWoodCost() && this.iron >= arrowTower.getIronCost() && this.mana >= arrowTower.getManaCost()) {
                    this.army.get(arrow_tower_index).add(arrowTower);
                    this.food -= arrowTower.getFoodCost();
                    this.wood -= arrowTower.getWoodCost();
                    this.iron -= arrowTower.getIronCost();
                    this.mana -= arrowTower.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Arrow Towers";
                    String message = "Faltan recursos para crear más Arrow Towers";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Arrow Towers");
        }
        return new String[]{"Se han agregado " + cnt + " Arrow Towers", ""};
    }
    
    public String[] newCatapultN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                Catapult catapult = new Catapult(getTechnologyDefense(), getTechnologyAttack());
                if (this.food >= catapult.getFoodCost() && this.wood >= catapult.getWoodCost() && this.iron >= catapult.getIronCost() && this.mana >= catapult.getManaCost()) {
                    this.army.get(catapult_index).add(catapult);
                    this.food -= catapult.getFoodCost();
                    this.wood -= catapult.getWoodCost();
                    this.iron -= catapult.getIronCost();
                    this.mana -= catapult.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Catapults";
                    String message = "Faltan recursos para crear más Catapults";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Catapults");
        }
        return new String[]{"Se han agregado " + cnt + " Catapults", ""};
    }
    
    public String[] newRocketLauncherTowerN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                RocketLauncherTower rocketLauncherTower = new RocketLauncherTower(getTechnologyDefense(), getTechnologyAttack());
                if (this.food >= rocketLauncherTower.getFoodCost() && this.wood >= rocketLauncherTower.getWoodCost() && this.iron >= rocketLauncherTower.getIronCost() && this.mana >= rocketLauncherTower.getManaCost()) {
                    this.army.get(rocket_launcher_index).add(rocketLauncherTower);
                    this.food -= rocketLauncherTower.getFoodCost();
                    this.wood -= rocketLauncherTower.getWoodCost();
                    this.iron -= rocketLauncherTower.getIronCost();
                    this.mana -= rocketLauncherTower.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Rocket Launcher Towers";
                    String message = "Faltan recursos para crear más Rocket Launcher Towers";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Rocket Launcher Towers");
        }
        return new String[]{"Se han agregado " + cnt + " Rocket Launcher Towers", ""};
    }

    public String[] newMagicianN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                Magician magician = new Magician(getTechnologyDefense(), getTechnologyAttack());
                if (this.magicTower < 1) {
                    String title = "Se han agregado " + cnt + " Magicians";
                    String message = "No tienes una Magic Tower para poder crear la unidad";
                    return new String[]{title, message};
                }
                if (this.food >= magician.getFoodCost() && this.wood >= magician.getWoodCost() && this.iron >= magician.getIronCost() && this.mana >= magician.getManaCost()) {
                    this.army.get(magician_index).add(magician);
                    this.food -= magician.getFoodCost();
                    this.wood -= magician.getWoodCost();
                    this.iron -= magician.getIronCost();
                    this.mana -= magician.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Magicians";
                    String message = "Faltan recursos para añadir más Magicians";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Magicians");
        }
        return new String[]{"Se han agregado " + cnt + " Magicians", ""};
    }
    
    public String[] newPriestN(int n) {
        int cnt = 0;
        if (n < 1) {
            return null;
        } else {
            for (int i = 0; i < n; i++) {
                Priest priest = new Priest(getTechnologyDefense(), getTechnologyAttack());
                if (this.church < 1) {
                    String title = "Se han agregado " + cnt + " Priests";
                    String message = "No tienes una Church para poder crear la unidad";
                    return new String[]{title, message};
                }
                if (this.food >= priest.getFoodCost() && this.wood >= priest.getWoodCost() && this.iron >= priest.getIronCost() && this.mana >= priest.getManaCost()) {
                    this.army.get(priest_index).add(priest);
                    this.food -= priest.getFoodCost();
                    this.wood -= priest.getWoodCost();
                    this.iron -= priest.getIronCost();
                    this.mana -= priest.getManaCost();
                    cnt += 1;
                } else {
                    String title = "Se han agregado " + cnt + " Priests";
                    String message = "Faltan recursos para añadir más Priests";
                    return new String[]{title, message};
                }
            }
            System.out.println("Se han agregado " + cnt + " Priests");
        }
        return new String[]{"Se han agregado " + cnt + " Priests", ""};
    }
        

    public void newSwordsman(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                Swordsman Swordsman = new Swordsman(getTechnologyDefense(), getTechnologyAttack());
                if (this.food>= Swordsman.getFoodCost() && this.wood >= Swordsman.getWoodCost() && this.iron >= Swordsman.getIronCost() && this.mana >= Swordsman.getManaCost()){
                    this.army.get(swordsman_index).add(Swordsman);
                    this.food -= Swordsman.getFoodCost();
                    this.wood -= Swordsman.getWoodCost();
                    this.iron -= Swordsman.getIronCost();
                    this.mana -= Swordsman.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas Swordsmen");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }

    public void newCrossbow(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                Crossbow Crossbow = new Crossbow(getTechnologyDefense(), getTechnologyAttack());
                if (this.food>= Crossbow.getFoodCost() && this.wood >= Crossbow.getWoodCost() && this.iron >= Crossbow.getIronCost() && this.mana >= Crossbow.getManaCost()){
                    this.army.get(crossbow_index).add(Crossbow);
                    this.food -= Crossbow.getFoodCost();
                    this.wood -= Crossbow.getWoodCost();
                    this.iron -= Crossbow.getIronCost();
                    this.mana -= Crossbow.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas Crossbows");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }

    public void newSpearman(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                Spearman Spearman = new Spearman(getTechnologyDefense(), getTechnologyAttack());
                if (this.food>= Spearman.getFoodCost() && this.wood >= Spearman.getWoodCost() && this.iron >= Spearman.getIronCost() && this.mana >= Spearman.getManaCost()){
                    this.army.get(spearman_index).add(Spearman);
                    this.food -= Spearman.getFoodCost();
                    this.wood -= Spearman.getWoodCost();
                    this.iron -= Spearman.getIronCost();
                    this.mana -= Spearman.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas Spearmen");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }

    public void newCannon(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                Cannon Cannon = new Cannon(getTechnologyDefense(), getTechnologyAttack());
                if (this.food>= Cannon.getFoodCost() && this.wood >= Cannon.getWoodCost() && this.iron >= Cannon.getIronCost() && this.mana >= Cannon.getManaCost()){
                    this.army.get(cannon_index).add(Cannon);
                    this.food -= Cannon.getFoodCost();
                    this.wood -= Cannon.getWoodCost();
                    this.iron -= Cannon.getIronCost();
                    this.mana -= Cannon.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas Cannons");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }



    public void newArrowTower(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                ArrowTower ArrowTower = new ArrowTower(getTechnologyDefense(), getTechnologyAttack());
                if (this.food>= ArrowTower.getFoodCost() && this.wood >= ArrowTower.getWoodCost() && this.iron >= ArrowTower.getIronCost() && this.mana >= ArrowTower.getManaCost()){
                    this.army.get(arrow_tower_index).add(ArrowTower);
                    this.food -= ArrowTower.getFoodCost();
                    this.wood -= ArrowTower.getWoodCost();
                    this.iron -= ArrowTower.getIronCost();
                    this.mana -= ArrowTower.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas ArrowTowers");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }
    
    public void newCatapult(int n)throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                Catapult Catapult = new Catapult(getTechnologyDefense(), getTechnologyAttack());
                if (this.food>= Catapult.getFoodCost() && this.wood >= Catapult.getWoodCost() && this.iron >= Catapult.getIronCost() && this.mana >= Catapult.getManaCost()){
                    this.army.get(catapult_index).add(Catapult);
                    this.food -= Catapult.getFoodCost();
                    this.wood -= Catapult.getWoodCost();
                    this.iron -= Catapult.getIronCost();
                    this.mana -= Catapult.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas Catapults");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }

    public void newRocketLauncherTower(int n) throws ResourceException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                RocketLauncherTower RocketLauncherTower = new RocketLauncherTower(getTechnologyDefense(), getTechnologyAttack());
                if (this.food>= RocketLauncherTower.getFoodCost() && this.wood >= RocketLauncherTower.getWoodCost() && this.iron >= RocketLauncherTower.getIronCost() && this.mana >= RocketLauncherTower.getManaCost()){
                    this.army.get(rocket_launcher_index).add(RocketLauncherTower);
                    this.food -= RocketLauncherTower.getFoodCost();
                    this.wood -= RocketLauncherTower.getWoodCost();
                    this.iron -= RocketLauncherTower.getIronCost();
                    this.mana -= RocketLauncherTower.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas RocketLauncherTowers");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }

    public void newMagician(int n) throws ResourceException, BuildingException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                Magician Magician = new Magician(getTechnologyDefense(), getTechnologyAttack());
                if (this.magicTower < 1){
                    throw new BuildingException("No tienes una magicTower para poder crear la unidad");
                }
                if (this.food>= Magician.getFoodCost() && this.wood >= Magician.getWoodCost() && this.iron >= Magician.getIronCost() && this.mana >= Magician.getManaCost()){
                    this.army.get(magician_index).add(Magician);
                    this.food -= Magician.getFoodCost();
                    this.wood -= Magician.getWoodCost();
                    this.iron -= Magician.getIronCost();
                    this.mana -= Magician.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas Magicians");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }

    public int newPriest(int n) throws ResourceException, BuildingException {
        int cnt = 0;
        if (n < 1) {
            return cnt;
        } else {
            try {
                for (int i = 0; i < n; i++) {
                    Priest priest = new Priest(getTechnologyDefense(), getTechnologyAttack());
                    if (this.church < 1) {
                        System.out.println("Se han agregado " + cnt + " tropas");
                        throw new BuildingException("No tienes una Church para poder crear la unidad");
                    }
                    if (this.food >= priest.getFoodCost() && this.wood >= priest.getWoodCost() && this.iron >= priest.getIronCost() && this.mana >= priest.getManaCost()) {
                        this.army.get(priest_index).add(priest);
                        this.food -= priest.getFoodCost();
                        this.wood -= priest.getWoodCost();
                        this.iron -= priest.getIronCost();
                        this.mana -= priest.getManaCost();
                        cnt += 1;
                    } else {
                        System.out.println("Se han agregado " + cnt + " tropas");
                        throw new ResourceException("Faltan recursos para añadir más Priests");
                    }
                }
            } catch (ResourceException | BuildingException e) {
                System.out.println("Se han agregado " + cnt + " tropas");
                throw e;
            }
        }
        return cnt;
    }

    public void updateResourceGeneration(){
        this.foodGeneration = CIVILIZATION_FOOD_GENERATED+this.farm*CIVILIZATION_FOOD_GENERATED_PER_FARM;
        this.woodGeneration = CIVILIZATION_WOOD_GENERATED+this.carpentry*CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY;
        this.ironGeneration = CIVILIZATION_IRON_GENERATED+this.smithy*CIVILIZATION_IRON_GENERATED_PER_SMITHY;
        this.manaGeneration = 0+this.magicTower*CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
    }

    public void generateResources(){
        this.food += this.foodGeneration;
        this.wood += this.woodGeneration;
        this.iron += this.ironGeneration;
        this.mana += this.manaGeneration;
    }

    public void generateResourcesPerSecond(){
        // Calcula la generación por segundo dividiendo la generación total entre 60
        int foodPerSecond = Math.round((float) this.foodGeneration / 60);
        int woodPerSecond = Math.round((float) this.woodGeneration / 60);
        int ironPerSecond = Math.round((float) this.ironGeneration / 60);
        int manaPerSecond = Math.round((float) this.manaGeneration / 60);
        
        // Suma la generación por segundo al recurso correspondiente
        this.food += foodPerSecond;
        this.wood += woodPerSecond;
        this.iron += ironPerSecond;
        this.mana += manaPerSecond;
    }

    public static void gainExperience(){
        for(int i = 0; i<army.size(); i++){
            for(MilitaryUnit unit : army.get(i)){
                unit.setExperience(unit.getExperience()+1);
                unit.getActualArmor();
                unit.takeDamage(-(PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT));
                unit.setBaseDamage(unit.attack()+PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT);
            }
        }
    }

    public void printearBonito() {
        String str = "Nombre: " + this.name + " Comida: " + this.food + " Hierro: " + this.iron + " Wood: " + this.wood + " Mana: " + this.mana + " Defense Level: " + this.technologyDefense + " Attack Level: " + this.technologyAttack + " Magic Tower: " + this.magicTower + " Church: " + this.church + " Farms: " + this.farm + " Smithy: " + this.smithy + " Carpentry: " + this.carpentry + " Battle: " + this.battles + " F:" + this.foodGeneration + " I:" + this.ironGeneration + " W:" + this.woodGeneration + " M:" + this.manaGeneration + " Army: " + this.army;
        System.out.println(str);
    }
}