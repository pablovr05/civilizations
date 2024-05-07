package com.project;

public class Main {

    public static void main(String[] args) {
        AppData db = AppData.getInstance();

        String sql = "SELECT * FROM PLANTAS";
        System.out.println(db.query(sql));

        // Tancar la connexió amb la base de dades
        db.close();

        // Forçar la sortida del programa per no esperar a tancar la connexió amb 'MySQL'
        // Assegura't que en aquest punt totes les dades s'han guardat correctament
        if (!"test".equals(System.getProperty("enviroment"))) {
            System.exit(0);
        }
    }
}
