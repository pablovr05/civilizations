package com.project;

public class Main {

    public static void main(String[] args) {
        AppData db = AppData.getInstance();
        db.delCivlizations();
        System.out.println(db.addCivilization("Civilización_de_pablo"));
        System.out.println(db.addCivilization("Civilización_de_pablo"));
        System.out.println(db.getIdByName("Civilización_de_pablo"));
    }
}
