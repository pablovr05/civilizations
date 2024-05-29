package com.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BattleDAO {
    public void save(int civilization_id, Battle battle){
        AppData db = AppData.getInstance();

        String updateQuery = "INSERT INTO Battle_stats (Civilization_id, wood_acquired, iron_acquired, winner) VALUES ("+civilization_id+", "+battle.wasteWoodIron[0]+", "+battle.wasteWoodIron[1]+", '"+battle.winner+"')";

        db.update(updateQuery);
        
        List<Map<String, Object>> listaID = db.query("SELECT civilization_id FROM Battle_stats ORDER BY Civilization_id DESC LIMIT 1");
        BigDecimal idBigDecimal = (BigDecimal) listaID.get(0).get("CIVILIZATION_ID");
        int id = idBigDecimal.intValue();

        String[] tiposEnemigo = new String[]{"Swordsman", "Spearman", "Crossbow", "Cannon"};

        // añadir atacantes enemigos
        for(int i = 0; i<4; i++){
            updateQuery = "INSERT INTO Enemy_attack_stats (num_battle, civilization_id, type, initials, drops) VALUES ("+id+", "+civilization_id+", '"+tiposEnemigo[i]+"', "+battle.initialArmies.get(1)[i]+", "+battle.enemyDrops[i]+")";

            db.update(updateQuery);
        }

        String[] tiposCivilizacion = new String[]{"Swordsman", "Spearman", "Crossbow", "Cannon", "ArrowTower","Catapult","RocketLauncherTower", "Magician","Priest"};

        // añadir tropas civilizacion en la batalla
        for(int i = 0; i<9; i++){
            if(i<4){
                String tabla = "Civilization_attack_stats";
                updateQuery = "INSERT INTO "+tabla+" (num_battle, civilization_id, type, initials, drops) VALUES ("+id+", "+civilization_id+", '"+tiposCivilizacion[i]+"', "+battle.initialArmies.get(0)[i]+", "+battle.civilizationDrops[i]+")";
                
                db.update(updateQuery);

            }else if(i<7){
                String tabla = "Civilization_defense_stats";
                updateQuery = "INSERT INTO "+tabla+" (num_battle, civilization_id, type, initials, drops) VALUES ("+id+", "+civilization_id+", '"+tiposCivilizacion[i]+"', "+battle.initialArmies.get(0)[i]+", "+battle.civilizationDrops[i]+")";

                db.update(updateQuery);

            }else{
                String tabla = "Civilization_special_stats";
                updateQuery = "INSERT INTO "+tabla+" (num_battle, civilization_id, type, initials, drops) VALUES ("+id+", "+civilization_id+", '"+tiposCivilizacion[i]+"', "+battle.initialArmies.get(0)[i]+", "+battle.civilizationDrops[i]+")";

                db.update(updateQuery);

            }
        }

        // Falta la tabla de battle_log después de crear la lista de battle

        for(int i = 0; i<battle.desarrolloBatalla.size(); i++){
            updateQuery = "INSERT INTO Battle_log (civilization_id, num_battle, num_line, atacante_civilization, defensor_enemy, ataque_civilization, defensa_enemy, repite_civilization, atacante_enemy, defensor_civilization, ataque_enemy, defensa_civilization, repite_enemy)"+
                " VALUES ("+civilization_id+", "+id+", "+i+", '"+battle.desarrolloBatalla.get(i).get(0)[0]+"','"+battle.desarrolloBatalla.get(i).get(0)[1]+"', "+Integer.parseInt(battle.desarrolloBatalla.get(i).get(0)[2])+", "+Integer.parseInt(battle.desarrolloBatalla.get(i).get(0)[3])+", "+Integer.parseInt(battle.desarrolloBatalla.get(i).get(0)[4])+", '"+battle.desarrolloBatalla.get(i).get(1)[0]+"','"+battle.desarrolloBatalla.get(i).get(1)[1]+"', "+Integer.parseInt(battle.desarrolloBatalla.get(i).get(1)[2])+", "+Integer.parseInt(battle.desarrolloBatalla.get(i).get(1)[3])+", "+Integer.parseInt(battle.desarrolloBatalla.get(i).get(1)[4])+")";
        }

        db.update("COMMIT");
    }

    public static int[] queryEnemyArmy(int id_battle, int id_civilization){
        AppData db = AppData.getInstance();

        int[] enemyArmy = new int[4];

        List<Map<String, Object>> listaEnemigos = db.query("SELECT * FROM Enemy_attack_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> enemigo : listaEnemigos){
            String tipo = (String) enemigo.get("TYPE");
            BigDecimal cantidad = (BigDecimal) enemigo.get("INITIAL");

            switch (tipo){
                case "Swordsman":
                    enemyArmy[0] = cantidad.intValue();
                    break;
                case "Spearman":
                    enemyArmy[1] = cantidad.intValue();
                    break;
                case "Crossbow":
                    enemyArmy[2] = cantidad.intValue();
                    break;
                case "Cannon":
                    enemyArmy[3] = cantidad.intValue();
                    break;
            }
        }

        return enemyArmy;
    }

    public static int[] queryCivilizationArmy(int id_battle, int id_civilization){
        AppData db = AppData.getInstance();

        int[] civilizationArmy = new int[9];

        List<Map<String, Object>> listaCivilization = db.query("SELECT * FROM Civilization_attack_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> tropa : listaCivilization){
            String tipo = (String) tropa.get("TYPE");
            BigDecimal cantidad = (BigDecimal) tropa.get("INITIAL");

            switch (tipo){
                case "Swordsman":
                    civilizationArmy[0] = cantidad.intValue();
                    break;
                case "Spearman":
                    civilizationArmy[1] = cantidad.intValue();
                    break;
                case "Crossbow":
                    civilizationArmy[2] = cantidad.intValue();
                    break;
                case "Cannon":
                    civilizationArmy[3] = cantidad.intValue();
                    break;
            }
        }


        listaCivilization = db.query("SELECT * FROM Civilization_defense_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> tropa : listaCivilization){
            String tipo = (String) tropa.get("TYPE");
            BigDecimal cantidad = (BigDecimal) tropa.get("INITIAL");

            switch (tipo){
                case "ArrowTower":
                    civilizationArmy[4] = cantidad.intValue();
                    break;
                case "Catapult":
                    civilizationArmy[5] = cantidad.intValue();
                    break;
                case "RocketLauncherTower":
                    civilizationArmy[6] = cantidad.intValue();
                    break;
            }
        }


        listaCivilization = db.query("SELECT * FROM Civilization_special_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> tropa : listaCivilization){
            String tipo = (String) tropa.get("TYPE");
            BigDecimal cantidad = (BigDecimal) tropa.get("INITIAL");

            switch (tipo){
                case "Magician":
                    civilizationArmy[7] = cantidad.intValue();
                    break;
                case "Priest":
                    civilizationArmy[8] = cantidad.intValue();
                    break;
            }
        }

        return civilizationArmy;
    }

    public static int[] queryEnemyDrops(int id_battle, int id_civilization){
        AppData db = AppData.getInstance();

        int[] enemyArmy = new int[4];

        List<Map<String, Object>> listaEnemigos = db.query("SELECT * FROM Enemy_attack_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> enemigo : listaEnemigos){
            String tipo = (String) enemigo.get("TYPE");
            BigDecimal cantidad = (BigDecimal) enemigo.get("DROPS");

            switch (tipo){
                case "Swordsman":
                    enemyArmy[0] = cantidad.intValue();
                    break;
                case "Spearman":
                    enemyArmy[1] = cantidad.intValue();
                    break;
                case "Crossbow":
                    enemyArmy[2] = cantidad.intValue();
                    break;
                case "Cannon":
                    enemyArmy[3] = cantidad.intValue();
                    break;
            }
        }

        return enemyArmy;
    }

    public static int[] queryCivilizationDrops(int id_battle, int id_civilization){
        AppData db = AppData.getInstance();

        int[] civilizationArmy = new int[9];

        List<Map<String, Object>> listaCivilization = db.query("SELECT * FROM Civilization_attack_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> tropa : listaCivilization){
            String tipo = (String) tropa.get("TYPE");
            BigDecimal cantidad = (BigDecimal) tropa.get("DROPS");

            switch (tipo){
                case "Swordsman":
                    civilizationArmy[0] = cantidad.intValue();
                    break;
                case "Spearman":
                    civilizationArmy[1] = cantidad.intValue();
                    break;
                case "Crossbow":
                    civilizationArmy[2] = cantidad.intValue();
                    break;
                case "Cannon":
                    civilizationArmy[3] = cantidad.intValue();
                    break;
            }
        }


        listaCivilization = db.query("SELECT * FROM Civilization_defense_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> tropa : listaCivilization){
            String tipo = (String) tropa.get("TYPE");
            BigDecimal cantidad = (BigDecimal) tropa.get("DROPS");

            switch (tipo){
                case "ArrowTower":
                    civilizationArmy[4] = cantidad.intValue();
                    break;
                case "Catapult":
                    civilizationArmy[5] = cantidad.intValue();
                    break;
                case "RocketLauncherTower":
                    civilizationArmy[6] = cantidad.intValue();
                    break;
            }
        }


        listaCivilization = db.query("SELECT * FROM Civilization_special_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> tropa : listaCivilization){
            String tipo = (String) tropa.get("TYPE");
            BigDecimal cantidad = (BigDecimal) tropa.get("DROPS");

            switch (tipo){
                case "Magician":
                    civilizationArmy[7] = cantidad.intValue();
                    break;
                case "Priest":
                    civilizationArmy[8] = cantidad.intValue();
                    break;
            }
        }

        return civilizationArmy;
    }

    public static String getBattleWinner(int id_battle, int id_civilization){
        AppData db = AppData.getInstance();

        List<Map<String, Object>> listaGanador = db.query("SELECT winner FROM Battle_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        String ganador = (String) listaGanador.get(0).get("WINNER");

        return ganador;
    }

    public static int[] getBattleWaste(int id_battle, int id_civilization){
        AppData db = AppData.getInstance();

        int[] listaWaste = new int[2];
        List<Map<String, Object>> listaRecursos = db.query("SELECT winner FROM Battle_stats WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        listaWaste[0] = ((BigDecimal) listaRecursos.get(0).get("WOOD_ACQUIRED")).intValue();
        listaWaste[1] = ((BigDecimal) listaRecursos.get(0).get("IRON_ACQUIRED")).intValue();

        return listaWaste;
    }

    public static ArrayList<int[]> getInitialCosts(int id_battle, int id_civilization){
        ArrayList<int[]> listaCostes = new ArrayList<>();
        int[] costes = new int[3];
        int[] listaCivilization = queryCivilizationArmy(id_battle, id_civilization);
        int[] listaEnemy = queryEnemyArmy(id_battle, id_civilization);

        for(int i = 0; i<listaCivilization.length; i++){
            costes[0] += listaCivilization[i]*Variables.FOOD_COST_UNITS[i];
            costes[1] += listaCivilization[i]*Variables.WOOD_COST_UNITS[i];
            costes[2] += listaCivilization[i]*Variables.IRON_COST_UNITS[i];
        }

        listaCostes.add(costes);

        costes = new int[3];
        for(int i = 0; i<listaEnemy.length; i++){
            costes[0] += listaEnemy[i]*Variables.FOOD_COST_UNITS[i];
            costes[1] += listaEnemy[i]*Variables.WOOD_COST_UNITS[i];
            costes[2] += listaEnemy[i]*Variables.IRON_COST_UNITS[i];
        }
        listaCostes.add(costes);

        return listaCostes;
    }

    public static ArrayList<int[]> getBattleLosses(int id_battle, int id_civilization){
        ArrayList<int[]> listaLosses = new ArrayList<>();
        int[] costes = new int[3];
        int[] listaCivilization = queryCivilizationDrops(id_battle, id_civilization);
        int[] listaEnemy = queryEnemyDrops(id_battle, id_civilization);

        for(int i = 0; i<listaCivilization.length; i++){
            costes[0] += listaCivilization[i]*Variables.FOOD_COST_UNITS[i];
            costes[1] += listaCivilization[i]*Variables.WOOD_COST_UNITS[i];
            costes[2] += listaCivilization[i]*Variables.IRON_COST_UNITS[i];
        }

        listaLosses.add(costes);

        costes = new int[3];
        for(int i = 0; i<listaEnemy.length; i++){
            costes[0] += listaEnemy[i]*Variables.FOOD_COST_UNITS[i];
            costes[1] += listaEnemy[i]*Variables.WOOD_COST_UNITS[i];
            costes[2] += listaEnemy[i]*Variables.IRON_COST_UNITS[i];
        }
        listaLosses.add(costes);

        return listaLosses;
    }

    public static ArrayList<ArrayList<String[]>> getDesarrolloBatalla(int id_battle, int id_civilization){
        AppData db = AppData.getInstance();

        ArrayList<ArrayList<String[]>> lista = new ArrayList<>();

        List<Map<String, Object>> query = db.query("SELECT * FROM Battle_log WHERE num_battle = "+id_battle+" and civilization_id = "+id_civilization);

        for(Map<String, Object> entrada : query){
            ArrayList<String[]> turnoCompleto = new ArrayList<>();
            String[] turnoCivilization = new String[5];
            String[] turnoEnemy = new String[5];

            turnoCivilization[0] =(String) entrada.get("ATACANTE_CIVILIZATION");
            turnoCivilization[1] =(String) entrada.get("DEFENSOR_ENEMY");
            turnoCivilization[2] =String.valueOf(((BigDecimal) entrada.get("ATAQUE_CIVILIZATION")).intValue());
            turnoCivilization[3] =String.valueOf(((BigDecimal) entrada.get("DEFENSA_ENEMY")).intValue());
            turnoCivilization[4] =String.valueOf(((BigDecimal) entrada.get("REPITE_CIVILIZATION")).intValue());


            turnoEnemy[0] =(String) entrada.get("ATACANTE_ENEMY");
            turnoEnemy[1] =(String) entrada.get("DEFENSOR_CIVILIZATION");
            turnoEnemy[2] =String.valueOf(((BigDecimal) entrada.get("ATAQUE_ENEMY")).intValue());
            turnoEnemy[3] =String.valueOf(((BigDecimal) entrada.get("DEFENSA_CIVILIZATION")).intValue());
            turnoEnemy[4] =String.valueOf(((BigDecimal) entrada.get("REPITE_ENEMY")).intValue());

            turnoCompleto.add(turnoCivilization);
            turnoCompleto.add(turnoEnemy);
            lista.add(turnoCompleto);
        }

        return lista;
    }
}
