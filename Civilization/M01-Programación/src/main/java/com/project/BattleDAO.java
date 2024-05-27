package com.project;

import java.math.BigDecimal;
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
            
        }

        db.update("COMMIT");
    }
}
