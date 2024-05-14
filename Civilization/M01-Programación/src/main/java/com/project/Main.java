package com.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<ArrayList<MilitaryUnit>> enemyArmy;

    public static void main(String[] args) throws ResourceException, BuildingException {
        Civilization civilization = new Civilization();
        civilization.setFood(20000);
        civilization.setIron(200000);
        civilization.setMana(200000);
        civilization.setWood(200000);
        PrintMenuPrincipal(civilization);
    }

    private static void PrintMenuPrincipal(Civilization civilization) throws ResourceException{
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        String error = "";
        String cabecera = ("""
            a88888b.  dP dP     dP dP dP        dP d8888888P  .d888888  d888888P dP  .88888.  888888ba  .d88888b  
            d8'   `88 88 88     88 88 88        88      .d8' d8'    88     88    88 d8'   `8b 88    `8b 88.    "' 
            88        88 88    .8P 88 88        88    .d8'   88aaaaa88a    88    88 88     88 88     88 `Y88888b. 
            88        88 88    d8' 88 88        88  .d8'     88     88     88    88 88     88 88     88       `8b 
            Y8.   .88 88 88  .d8P  88 88        88 d8'       88     88     88    88 Y8.   .8P 88     88 d8'   .8P 
             Y88888P' dP 888888'   dP 88888888P dP Y8888888P 88     88     dP    dP  `8888P'  dP     dP  Y88888P  
            """);
        while (true){
            limpiarTerminal();
            Scanner scanner = new Scanner(System.in);
            System.out.print(cabecera);
            System.out.println("                                               Hecho por Joel Martinez, Adria Martinez, Pablo Vicente");
            System.out.println("\n\n          1. Crear edificios");
            System.out.println("\n          2. Añadir tropas al ejército");
            System.err.println("\n          3. Ver estadisticas de la civilización");
            System.out.println("\n          4. Ver historial de ataques\n\n");
            if (error != ""){
                System.err.println(ANSI_RED + error + ANSI_RESET);
            }
            System.out.print("          Escoja una opcion [1,2,3,4]: ");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    PrintMenuEdificios(civilization);
                    break;
                case 2:
                    PrintMenuTropas();
                    break;
                case 3:
                    PrintMenuStats();
                    break;
                case 4:
                    PrintMenuHistorial();
                    break;
                default:
                    error = "          Opción " + opcion + " no es vàlida, vuelva a intentar.";
            }
        }
    }

    private static void PrintMenuEdificios(Civilization civilization) throws ResourceException{
        String castillo = """
                     █▄██▄█
            █▄█▄█▄█▄█▐█┼██▌█▄█▄█▄█▄█
            ███┼█████▐████▌█████┼███
            █████████▐████▌█████████
        """;
        String error = "";
        while (true){
            limpiarTerminal();
            System.out.println("\n    Crear Edificios");
            System.out.println("\n\n" + castillo);
            System.out.println("\n\n            - Farm: "+civilization.getFarm());
            System.out.println("            - Smithy: "+civilization.getSmithy());
            System.out.println("            - Carpentry: "+civilization.getCarpentry());
            System.out.println("            - Magic Tower: "+civilization.getMagicTower());
            System.out.println("            - Church: "+civilization.getChurch());
            Scanner scanner = new Scanner(System.in);
            System.out.print(" \n\n   Indica que acción quiere realizar [Help]: ");
            String opcion = scanner.nextLine();
            String[] comanda = opcion.toLowerCase().split(" ");
            if (comanda[0].equals("add")){
                switch (comanda[1]){
                    case "farm":
                        civilization.newFarm();
                        break;
                    case "smithy":
                        civilization.newSmithy();
                        break;
                }

            }
        }
    }

    private static void PrintMenuTropas(){
        System.out.println("a");
    
    }
    private static void PrintMenuStats(){
        
    }

    private static void PrintMenuHistorial(){
        System.out.println("a");
    }

    public static void limpiarTerminal() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<MilitaryUnit>> createEnemyArmy(int battles){
        ArrayList<ArrayList<MilitaryUnit>> enemyArmy = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            enemyArmy.add(new ArrayList<>());
        }
        int madera = Variables.WOOD_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.WOOD_BASE_ENEMY_ARMY/100);
        int comida = Variables.FOOD_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.FOOD_BASE_ENEMY_ARMY/100);
        int hierro = Variables.IRON_BASE_ENEMY_ARMY + (battles*Variables.ENEMY_FLEET_INCREASE*Variables.IRON_BASE_ENEMY_ARMY/100);

        while(madera>=Variables.WOOD_COST_SWORDSMAN && comida >=Variables.FOOD_COST_SWORDSMAN && hierro>=Variables.IRON_COST_SWORDSMAN){
            int prob = enemyCreationAvailable(madera, comida, hierro); 
            int chance = (int) (Math.random()*prob);
            System.out.println(chance);
            if(chance<= 35){
                enemyArmy.get(0).add(new Swordsman());
                madera -= Variables.WOOD_COST_SWORDSMAN;
                comida -= Variables.FOOD_COST_SWORDSMAN;
                hierro -= Variables.IRON_COST_SWORDSMAN;
            } else if(chance<=60){
                enemyArmy.get(1).add(new Spearman());
                madera -= Variables.WOOD_COST_SPEARMAN;
                comida -= Variables.FOOD_COST_SPEARMAN;
                hierro -= Variables.IRON_COST_SPEARMAN;
            } else if(chance<=80){
                enemyArmy.get(2).add(new Crossbow());
                madera -= Variables.WOOD_COST_CROSSBOW;
                comida -= Variables.FOOD_COST_CROSSBOW;
                hierro -= Variables.IRON_COST_CROSSBOW;
            } else if(chance<=100){
                enemyArmy.get(3).add(new Cannon());
                madera -= Variables.WOOD_COST_CANNON;
                comida -= Variables.FOOD_COST_CANNON;
                hierro -= Variables.IRON_COST_CANNON;
            }
        }

        return enemyArmy;
    }

    private static int enemyCreationAvailable(int madera, int comida, int hierro){
        if(madera>=Variables.WOOD_COST_CANNON && comida >=Variables.FOOD_COST_CANNON && hierro>=Variables.IRON_COST_CANNON){
            return 100;
        } else if(madera>=Variables.WOOD_COST_CROSSBOW && comida >=Variables.FOOD_COST_CROSSBOW && hierro>=Variables.IRON_COST_CROSSBOW){
            return 80;
        } else if(madera>=Variables.WOOD_COST_SPEARMAN && comida >=Variables.FOOD_COST_SPEARMAN && hierro>=Variables.IRON_COST_SPEARMAN){
            return 60;
        } else if(madera>=Variables.WOOD_COST_SWORDSMAN && comida >=Variables.FOOD_COST_SWORDSMAN && hierro>=Variables.IRON_COST_SWORDSMAN){
            return 35;
        } else {
            return 0;
        }
    }

    private static void viewThreat(){
        String swordsman = String.format("%-" + 20 + "s", "Swordsman");
        String spearman = String.format("%-" + 20 + "s", "Spearman");
        String crossbow = String.format("%-" + 20 + "s", "Crossbow");
        String cannon = String.format("%-" + 20 + "s", "Cannon");

        System.out.println("NEW threat INCOMING\n");
        System.out.println(swordsman+enemyArmy.get(0).size());
        System.out.println(spearman+enemyArmy.get(1).size());
        System.out.println(crossbow+enemyArmy.get(2).size());
        System.out.println(cannon+enemyArmy.get(3).size());
    }
}
