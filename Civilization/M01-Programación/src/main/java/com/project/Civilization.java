package com.project;

import java.util.ArrayList;

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
    public int technologyAtack;
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
        this.technologyDefense = 1;
        this.technologyAtack = 1;
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
        for(int i = 0; i<9; i++){
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
    public void setTechnologyAtack(int technologyAtack) {
        this.technologyAtack = technologyAtack;
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
    public int getTechnologyAtack() {
        return technologyAtack;
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
        if(this.food>= 5000 && this.wood >=10000 && this.iron >= 12000){
            this.food -= 5000;
            this.wood -= 10000;
            this.iron -= 12000;
            this.technologyDefense += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }
    public void upgradeTechnologyAttack(){
        if(this.food>= 5000 && this.wood >=10000 && this.iron >= 12000){
            this.food -= 5000;
            this.wood -= 10000;
            this.iron -= 12000;
            this.technologyAtack += 1;
        }else{
            // throw new ResourceException(""); 
        }
    }

    public void newArrowTower(int n){
        if (n < 1){
            return;
        }
        else{
            for(int i=0;i<n;i++){
                ArrowTower ArrowTower = new ArrowTower(getTechnologyDefense(), getTechnologyAtack());
                if (this.food>= ArrowTower.getFoodCost() && this.wood >= ArrowTower.getWoodCost() && this.iron >= ArrowTower.getIronCost() && this.mana >= ArrowTower.getManaCost()){
                    this.army.get(arrow_tower_index).add(ArrowTower);
                }
                else{
                    System.out.println("Se han agregado " + i + " tropas");
                    break;
                    //throw new ResourceException("");
                }
            }
        }
        
    }
}
