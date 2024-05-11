package com.project;

public class Main {

    public static void main(String[] args) {
        addCivlization_stats("Civilizacion de Jose");
    }

    public static void addCivlization_stats(String name){
        AppData db = AppData.getInstance();
        String sql = "INSERT INTO Civilization_stats (name,wood_amount,iron_amount,food_amount,mana_amount,magicTower_counter,church_counter,farm_counter,smithy_counter,carpentry_counter,technology_defense_level,technology_attack_level,battles_counter) VALUES ('" + name + "',10000,10000,10000,10000,0,0,0,0,0,0,0,0)";
        db.update(sql);
        System.out.println(db.query("SELECT * FROM Civilization_stats"));
    }
}

