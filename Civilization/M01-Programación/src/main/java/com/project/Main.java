package com.project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class Main {
    public static ArrayList<ArrayList<MilitaryUnit>> enemyArmy;

    public static void main(String[] args) throws ResourceException, BuildingException{
        Civilization civilization = new Civilization();
        civilization.setFood(200000);
        civilization.setIron(200000);
        civilization.setMana(200000);
        civilization.setWood(200000);

        Timer timer = new Timer();
        int tiempoGeneracionRecursos = 60;
        TimerTask generarRecursos = new TimerTask() {
            int segundos = tiempoGeneracionRecursos;

            @Override
            public void run(){
                if(segundos>0){
                    segundos--;
                } else{
                    civilization.generateResources();
                    segundos = tiempoGeneracionRecursos;
                }
            }
        };
        int tiempoGenerarBatalla = 180;
        TimerTask generateBattle = new TimerTask() {
            int segundos = 10;

            @Override
            public void run(){
                if(segundos>0){
                    segundos--;
                } else {
                    PrintMenuBatalla(civilization);
                    segundos = tiempoGenerarBatalla;
                }
            }
        };

        timer.scheduleAtFixedRate(generarRecursos, 0, 1000);
        // timer.scheduleAtFixedRate(generateBattle, 0, 1000);

        PrintMenuPrincipal(civilization);
        
    }

    private static void PrintMenuPrincipal(Civilization civilization) throws ResourceException, BuildingException{
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        Scanner scanner = new Scanner(System.in);
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
            Scanner main = new Scanner(System.in);
            limpiarTerminal();
            System.out.print(cabecera);
            System.out.println("                                               Hecho por Joel Martinez, Adria Martinez, Pablo Vicente");
            System.out.println("\n\n                                                    Wood: " + civilization.getWood());
            System.out.println("                                                    Iron: " + civilization.getIron());
            System.out.println("                                                    Mana: " + civilization.getMana());
            System.out.println("                                                    Food: " + civilization.getFood());
            System.out.println("\n\n          1. Crear edificios");
            System.out.println("\n          2. Añadir tropas al ejército");
            System.out.println("\n          3. Mejorar Tecnologias");
            System.err.println("\n          4. Ver estadisticas de la civilización");
            System.out.println("\n          5. Ver historial de ataques");
            System.out.println("\n          6. Batalla");
            System.out.println("\n          0. Salir\n\n");
            if (error != ""){
                System.err.println(ANSI_RED + error + ANSI_RESET);
            }
            System.out.print("          Escoja una opcion [1,2,3,4,0]: ");
            try{int opcion = main.nextInt();
                main.nextLine();
                switch (opcion) {
                    case 1:
                        PrintMenuEdificios(civilization);
                        break;
                    case 2:
                        PrintMenuTropas(civilization);
                        break;
                    case 3:
                        PrintMenuUpgrade(civilization);
                        break;
                    case 4:
                        PrintMenuStats(civilization);
                        break;
                    case 5:
                        PrintMenuHistorial();
                        break;
                    case 6:
                        enemyArmy = createEnemyArmy(civilization.battles);
                        Battle batalla = new Battle(civilization.army, enemyArmy);
                        String ganador = batalla.startBattle();
                        System.out.println("ha ganado "+ganador);
                        System.out.print("Quieres ver el desarrollo de la batalla? (S/N)");
                        String verDesarrollo = scanner.nextLine();

                        switch (verDesarrollo){
                            case "S":
                                System.out.println(batalla.getBattleDevelopment());
                                String vacio = scanner.nextLine();
                                
                            default:
                                System.out.println("Te doy las opciones atontao, escoge una.");
                        }
                        break;
                        
                    case 7:
                        viewThreat();
                        String vacio = scanner.nextLine();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        error = "          Opción " + opcion + " no es vàlida, vuelva a intentar.";
                }
            }catch (InputMismatchException e) {
                error = "          No has introducido un numero, vuelva a intentar";
            }
        }
    }

    private static void PrintMenuUpgrade(Civilization civilization) throws ResourceException, BuildingException{
        String espada = """
                ()xxxxx[[{:::::::::::::::::::::::::::::::::::::::>
        """;
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        String error = "";
        while (true){
            Scanner scanner = new Scanner(System.in);
            int acomida = Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST+civilization.technologyAttack*Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST;
            int amadera = Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST+civilization.technologyAttack*Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST;
            int ahierro = Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST+civilization.technologyAttack*Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST;
            int dcomida = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST+civilization.technologyDefense*Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST;
            int dmadera = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST+civilization.technologyDefense*Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST;
            int dhierro = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST+civilization.technologyDefense*Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST;
            limpiarTerminal();
            System.out.println("\n    Mejorar Tecnologia\n"); 
            System.out.print(espada + "\n\n");
            System.out.println("              Civilization Attack: " + civilization.getTechnologyAttack() + "              Civilization Defense: " + civilization.getTechnologyDefense() + "\n\n");
            System.out.println("    1. Mejorar Attack [Wood: " + amadera+ ", Iron: " + ahierro + ", Food: " + acomida + "]");
            System.out.println("    2. Mejorar Defense [Wood: " + dmadera + ", Iron: " + dhierro + ", Food: " + dcomida + "]");
            System.out.println("    0. Salir\n");
            if (error != ""){
                System.out.println(ANSI_RED + error + ANSI_RESET);
            }
            System.out.print("          Escoja una opcion [1,2,0]: ");
            try{int opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        try {
                            civilization.upgradeTechnologyAttack();
                        }
                        catch (ResourceException e){
                            error = "          " + e.getMessage();
                        }
                        break;
                    case 2:
                        try {
                            civilization.upgradeTechnologyDefense();
                        }
                        catch (ResourceException e){
                            error = e.getMessage();
                        }
                        break;
                    case 0:
                        PrintMenuPrincipal(civilization);
                }
            }catch (InputMismatchException e) {
                error = "          No has introducido un numero, vuelva a intentar";
            }


        }

    }

    private static void PrintMenuEdificios(Civilization civilization) throws ResourceException, BuildingException{
        String castillo = """
                     █▄██▄█
            █▄█▄█▄█▄█▐█┼██▌█▄█▄█▄█▄█
            ███┼█████▐████▌█████┼███
            █████████▐████▌█████████
        """;
        String error = "";
        Scanner scanner = new Scanner(System.in);
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        while (true){
            limpiarTerminal();
            System.out.println("\n    Crear Edificios");
            System.out.println("\n\n" + castillo);
            System.out.println("\n\n            - Farm: "+civilization.getFarm());
            System.out.println("            - Smithy: "+civilization.getSmithy());
            System.out.println("            - Carpentry: "+civilization.getCarpentry());
            System.out.println("            - Magic Tower: "+civilization.getMagicTower());
            System.out.println("            - Church: "+civilization.getChurch());
            System.out.print(" \n\n   Indica que acción quiere realizar [Help,exit]: ");
            String opcion = scanner.nextLine();
            String[] comanda = opcion.toLowerCase().split(" ");
            if (comanda[0].equals("add") && comanda.length > 1){
                switch (comanda[1]){
                    case "farm":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newFarm(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newFarm(1);
                        }
                        break;
                    case "smithy":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newSmithy(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            try{
                                civilization.newSmithy(1);
                            }
                            catch (ResourceException e){
                                error = e.getMessage();
                            }
                        }
                        break;
                    case "church":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                try{
                                    civilization.newChurch(n);
                                }
                                catch (ResourceException e){
                                    error = e.getMessage();
                                }
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newChurch(1);
                        }
                        break;
                    case "magictower":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newMagicTower(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newMagicTower(1);
                        }
                        break;
                    case "carpentry":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newCarpentry(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newCarpentry(1);
                        }
                        break;
                }
            }
            else if (comanda[0].equals("exit")){
                PrintMenuPrincipal(civilization);
            }
            else{
                System.out.println(error);
            }

            
        }
    }

    private static void PrintMenuTropas(Civilization civilization) throws ResourceException, BuildingException{
        String error = "";
        String escudo = """
            |\\                     /)
            /\\_\\\\__               (_//
           |   `>\\-`     _._       //`)        
            \\ /` \\\\  _.-`:::`-._  //
             `    \\|`    :::    `|/
                   |     :::     |
                   |.....:::.....|
                   |:::::::::::::|
                   |     :::     |
                   \\     :::     /
                    \\    :::    /
                     `-. ::: .-'
                      //`:::`\\\\
                     //   '   \\\\
                    |/         \\\\
            """;
        Scanner scanner = new Scanner(System.in);
        while (true){
            limpiarTerminal();
            System.out.println("\n    Añadir Tropas");
            System.out.println("\n\n" + escudo);
            System.out.println("   Swordsmen: " + civilization.army.get(0).size() + "              Spearmen: " + civilization.army.get(1).size() + "              Crossbows: " + civilization.army.get(2).size() + "              Cannons: " + civilization.army.get(3).size());
            System.out.println("   Arrow Towers: " + civilization.army.get(4).size() + "           Catapults: " + civilization.army.get(5).size() + "             Rocket Launcher Towers: " + civilization.army.get(6).size());
            System.out.println("   Magicians: " + civilization.army.get(7).size() + "              Priests: " + civilization.army.get(8).size());
            System.out.print("\n\n   Indica que acción quiere realizar [Help,exit]: ");
            String opcion = scanner.nextLine();
            String[] comanda = opcion.toLowerCase().split(" ");
            if (comanda[0].equals("add") && comanda.length > 1){
                switch (comanda[1]){
                    case "swordsman":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newSwordsman(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newSwordsman(1);
                        }
                        break;
                    case "spearman":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newSpearman(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newSpearman(1);
                        }
                        break;
                    case "cannon":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newCannon(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newCannon(1);
                        }
                        break;
                    case "crossbow":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newCrossbow(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newCrossbow(1);
                        }
                        break;
                    
                    case "arrowtower":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newArrowTower(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newArrowTower(1);
                        }
                        break;
                    case "catapult":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newCatapult(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newCatapult(1);
                        }
                        break;
                    case "rocketlaunchertower":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newRocketLauncherTower(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newRocketLauncherTower(1);
                        }
                        break;
                    case "priest":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                int cnt = civilization.newPriest(n);
                                List<String> opciones = new ArrayList<>();
                                opciones.add("swordsman");
                                opciones.add("spearman");
                                opciones.add("crossbow");
                                opciones.add("cannon");
                                opciones.add("arrowtower");
                                opciones.add("catapult");
                                opciones.add("rocketlaunchertower");

                                for (int i=0; i<cnt; i++){
                                    String sanctify = "";
                                    while (!opciones.contains(sanctify)){
                                        System.out.print("Que tipo de unidad quieres santificar?: ");
                                        sanctify = scanner.nextLine();
                                        switch(sanctify){
                                            case "swordsman":
                                                civilization.sanctifyGroup(0);
                                                break;
                                            case "spearman":
                                                civilization.sanctifyGroup(1);
                                                break;
                                            case "crossbow":
                                                civilization.sanctifyGroup(2);
                                                break;
                                            case "cannon":
                                                civilization.sanctifyGroup(3);
                                                break;
                                            case "arrowtower":
                                                civilization.sanctifyGroup(4);
                                                break;
                                            case "catapult":
                                                civilization.sanctifyGroup(5);
                                                break;
                                            case "rocketlaunchertower":
                                                civilization.sanctifyGroup(6);
                                                break;
                                        }
                                    }  
                                }
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            int cnt = civilization.newPriest(1);
                            List<String> opciones = new ArrayList<>();
                            opciones.add("swordsman");
                            opciones.add("spearman");
                            opciones.add("crossbow");
                            opciones.add("cannon");
                            opciones.add("arrowtower");
                            opciones.add("catapult");
                            opciones.add("rocketlaunchertower");
                            String sanctify = "";
                                while (!opciones.contains(sanctify)){
                                    System.out.print("  Que tipo de unidad quieres santificar?: ");
                                    sanctify = scanner.nextLine();
                                    switch(sanctify){
                                        case "swordsman":
                                            civilization.sanctifyGroup(0);
                                            break;
                                        case "spearman":
                                            civilization.sanctifyGroup(1);
                                            break;
                                        case "crossbow":
                                            civilization.sanctifyGroup(2);
                                            break;
                                        case "cannon":
                                            civilization.sanctifyGroup(3);
                                            break;
                                        case "arrowtower":
                                            civilization.sanctifyGroup(4);
                                            break;
                                        case "catapult":
                                            civilization.sanctifyGroup(5);
                                            break;
                                        case "rocketlaunchertower":
                                            civilization.sanctifyGroup(6);
                                            break;
                                    }
                                }
                        }
                        break;
                    case "magician":
                        if(comanda.length == 3){
                            try{
                                int n = Integer.parseInt(comanda[2]);
                                civilization.newMagician(n);
                            }
                            catch (NumberFormatException e) {
                                error = "No has introducido una cantidad válida";
                            }
                            
                        }else {
                            civilization.newMagician(1);
                        }
                        break;
                }
            }
            else if (comanda[0].equals("exit")){
                PrintMenuPrincipal(civilization);
            }
            else{
                System.out.println(error);
            }

            
        }
    
    }
    private static void PrintMenuStats(Civilization civilization) throws ResourceException, BuildingException{
        String error = "";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        while (true){
            limpiarTerminal();
            civilization.printStats();
            System.out.println("\n");
            Scanner scanner = new Scanner(System.in);
            if (error != ""){
                System.out.println(error);
            }
            System.out.print("Escriba 'exit' para salir: ");
            try{
                String opcion = scanner.nextLine();
                if (opcion.equals("exit")){
                    PrintMenuPrincipal(civilization);
                }
                else{
                    error = ANSI_RED + "Opcion no valida, vuelva a intentar" + ANSI_RESET;
                }
            } catch (InputMismatchException e){
                error = ANSI_RED + "Opcion no valida, vuelva a intentar" + ANSI_RESET;
            }

        }
    }

    private static void PrintMenuHistorial(){
        System.out.println("a");
    }

    private static void PrintMenuBatalla(Civilization civilization){
        String error = "";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        Scanner scanner = new Scanner(System.in);
        while(true){
            limpiarTerminal();
            System.out.println("\n\n          1. Ver ejercito civilizacion");
            System.out.println("\n          2. Ver ejercito enemigo");
            System.out.println("\n          3. Comenzar Batalla");
            System.out.println("\n          Escoje una opción [1-3]: ");
            try{
                int opcion = scanner.nextInt();
                if(opcion == 1){
                    civilization.printArmy();
                    System.out.println();
                    String enter = scanner.nextLine();
                }
            } catch (InputMismatchException e){
                error = ANSI_RED + "Opcion no valida, vuelva a intentar" + ANSI_RESET;

            }
        }
        
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
