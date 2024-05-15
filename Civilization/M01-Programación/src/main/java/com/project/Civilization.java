package com.project;

import java.util.ArrayList;
import java.util.List;

public class Civilization implements Variables {
    private final int swordsman_index = 0;
    private final int spearman_index = 1;
    private final int crossbow_index = 2;
    private final int cannon_index = 3;
    private final int arrow_tower_index = 4;
    private final int catapult_index = 5;
    private final int rocket_launcher_index = 6;
    private final int magician_index = 7;
    private final int priest_index = 8;

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
    public ArrayList<ArrayList<MilitaryUnit>> army = new ArrayList<>();
    
    public Civilization(){
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
        for(int i = 0; i<9; i++){
            army.add(new ArrayList<>());
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
    }
    public void newFarm(int n) throws ResourceException{
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

    public void newPriest(int n) throws ResourceException, BuildingException{
        int cnt = 0;
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                Priest Priest = new Priest(getTechnologyDefense(), getTechnologyAttack());
                if (this.church < 1){
                    throw new BuildingException("No tienes una Church para poder crear la unidad");
                }
                if (this.food>= Priest.getFoodCost() && this.wood >= Priest.getWoodCost() && this.iron >= Priest.getIronCost() && this.mana >= Priest.getManaCost()){
                    this.army.get(priest_index).add(Priest);
                    this.food -= Priest.getFoodCost();
                    this.wood -= Priest.getWoodCost();
                    this.iron -= Priest.getIronCost();
                    this.mana -= Priest.getManaCost();
                    cnt += 1;
                }
                else{
                    System.out.println("Se han agregado " + cnt + " tropas");
                    throw new ResourceException("Faltan recursos para añadir mas Priests");
                }
            }
            System.out.println("Se han agregado " + cnt + " tropas");
        }
    }

    public void updateResourceGeneration(){
        this.foodGeneration = CIVILIZATION_FOOD_GENERATED+this.farm*CIVILIZATION_FOOD_GENERATED_PER_FARM;
        this.woodGeneration = CIVILIZATION_WOOD_GENERATED+this.carpentry*CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY;
        this.ironGeneration = CIVILIZATION_IRON_GENERATED+this.smithy*CIVILIZATION_IRON_GENERATED_PER_SMITHY;
        this.manaGeneration = 0+this.magicTower*CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
    }
}
