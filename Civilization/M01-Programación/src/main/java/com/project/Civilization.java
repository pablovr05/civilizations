package com.project;

import java.util.ArrayList;
import java.util.List;

public class Civilization implements Variables{
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
        for(int i = 0; i>9; i++){
            ArrayList<MilitaryUnit> list = new ArrayList<>();
            army.add(list);
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

    public void newChurch(){
        if(this.food>= FOOD_COST_CHURCH && this.wood >= WOOD_COST_CHURCH && this.iron >= IRON_COST_CHURCH && this.mana >= MANA_COST_CHURCH){
            this.food -= FOOD_COST_CHURCH;
            this.wood -= WOOD_COST_CHURCH;
            this.iron -= IRON_COST_CHURCH;
            this.mana -= MANA_COST_CHURCH;
            this.church += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }
    public void newMagicTower(){
        if(this.food>= FOOD_COST_MAGICTOWER && this.wood >= WOOD_COST_MAGICTOWER && this.iron >= IRON_COST_MAGICTOWER){
            this.food -= FOOD_COST_MAGICTOWER;
            this.wood -= WOOD_COST_MAGICTOWER;
            this.iron -= IRON_COST_MAGICTOWER;
            this.magicTower += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }
    public void newFarm(){
        if(this.food>= FOOD_COST_FARM && this.wood >= WOOD_COST_FARM && this.iron >= IRON_COST_FARM){
            this.food -= FOOD_COST_FARM;
            this.wood -= WOOD_COST_FARM;
            this.iron -= IRON_COST_FARM;
            this.farm += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }
    public void newCarpentry(){
        if(this.food>= FOOD_COST_CARPENTRY && this.wood >= WOOD_COST_CARPENTRY && this.iron >= IRON_COST_CARPENTRY){
            this.food -= FOOD_COST_CARPENTRY;
            this.wood -= WOOD_COST_CARPENTRY;
            this.iron -= IRON_COST_CARPENTRY;
            this.carpentry += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }
    public void newSmithy(){
        if(this.food>= FOOD_COST_SMITHY && this.wood >= WOOD_COST_SMITHY && this.iron >= IRON_COST_SMITHY){
            this.food -= FOOD_COST_SMITHY;
            this.wood -= WOOD_COST_SMITHY;
            this.iron -= IRON_COST_SMITHY;
            this.smithy += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }
    public void upgradeTechnologyDefense(){
        int comida = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST+this.technologyDefense*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST;
        int madera = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST+this.technologyDefense*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST;
        int hierro = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST+this.technologyDefense*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST;
        if(this.food >= comida && this.wood >= madera && this.iron >= hierro){
            this.food -= comida;
            this.wood -= madera;
            this.iron -= hierro;
            this.technologyDefense += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }
    public void upgradeTechnologyAttack(){
        int comida = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST+this.technologyAttack*UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST;
        int madera = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST+this.technologyAttack*UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST;
        int hierro = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST+this.technologyAttack*UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST;
        if(this.food >= comida && this.wood >= madera && this.iron >= hierro){
            this.food -= comida;
            this.wood -= madera;
            this.iron -= hierro;
            this.technologyAttack += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }

    public void printStats(){
        System.out.println("----CIVILIZATION STATS----");
        System.out.println("TECHNOLOGY");
        System.out.println("-Attack: "+this.technologyAttack);
        System.out.println("-Defense: "+this.technologyDefense);
        System.out.println("BUILDINGS");
        System.out.println("-Farm: "+this.farm);
        System.out.println("-Smithy: "+this.smithy);
        System.out.println("-Carpentry: "+this.carpentry);
        System.out.println("-Magic Tower: "+this.magicTower);
        System.out.println("-Church: "+this.church);
        System.out.println("DEFENSE UNITS");
        System.out.println("-Arrow Tower: "+this.army.get(arrow_tower_index).size());
        System.out.println("-Catapult: "+this.army.get(catapult_index).size());
        System.out.println("-Rocket Launcher: "+this.army.get(rocket_launcher_index).size());
        System.out.println("ATTACK UNITS");
        System.out.println("-Swordsman: "+this.army.get(swordsman_index).size());
        System.out.println("-Spearman: "+this.army.get(spearman_index).size());
        System.out.println("-Crossbow: "+this.army.get(crossbow_index).size());
        System.out.println("-Cannon: "+this.army.get(cannon_index).size());
        System.out.println("ESPECIAL UNITS");
        System.out.println("-Magician: "+this.army.get(magician_index).size());
        System.out.println("-Priest: "+this.army.get(priest_index).size());
        System.out.println("RESOURCES");
        System.out.println("-Food: "+this.food);
        System.out.println("-Wood: "+this.wood);
        System.out.println("-Iron: "+this.iron);
        System.out.println("-Mana: "+this.mana);
        System.out.println("GENERATION RESOURCES");
        System.out.println("-Food: "+this.food);
        System.out.println("-Wood: "+this.wood);
        System.out.println("-Iron: "+this.iron);
        System.out.println("-Mana: "+this.mana);
    }
}
