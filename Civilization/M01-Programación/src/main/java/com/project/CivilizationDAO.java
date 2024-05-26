package com.project;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import oracle.net.aso.c;

public class CivilizationDAO {
    public void save(Civilization civilization){
        AppData db = AppData.getInstance();

        BigDecimal id = getIdByName(civilization.name);

        db.update("UPDATE Civilization_stats SET wood_amount = "+civilization.wood+", food_amount = "+civilization.food+", iron_amount = "+civilization.iron+", mana_amount = "+civilization.mana+", magicTower_counter = "+civilization.magicTower+", church_counter"+ civilization.church+", farm_count = "+civilization.farm+", smithy_count = "+civilization.smithy+", carpentry_count = "+civilization.carpentry+", technology_defense_level = "+civilization.technologyDefense+", technology_attack_level = "+civilization.technologyAttack+", battles_counter"+civilization.battles);
        db.update("COMMIT");
    }

    public void loadUnits(Civilization civilization, int id) {
        loadAttackUnits(civilization, id);
        loadDefenseUnits(civilization, id);
        loadSpecialUnits(civilization, id);
    }

    private void loadAttackUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM attack_units_stats WHERE civilization_id = ?"+ id);
    
        for (Map<String, Object> unitData : query) {
            String type = (String) unitData.get("type");
            int unitId = (int) unitData.get("unit_id");
            int armor = (int) unitData.get("armor");
            int baseDamage = (int) unitData.get("base_damage");
            int experience = (int) unitData.get("experience");
            boolean sanctified = (int) unitData.get("sanctified") == 1;
    
            MilitaryUnit unit = createUnit(type, unitId, armor, baseDamage, experience, sanctified);
            addUnitToArmy(civilization, unit, type);
        }
    }
    
    private void loadDefenseUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM defense_units_stats WHERE civilization_id = ?"+id);
    
        for (Map<String, Object> unitData : query) {
            String type = (String) unitData.get("type");
            int unitId = (int) unitData.get("unit_id");
            int armor = (int) unitData.get("armor");
            int baseDamage = (int) unitData.get("base_damage");
            int experience = (int) unitData.get("experience");
            boolean sanctified = (int) unitData.get("sanctified") == 1;
    
            MilitaryUnit unit = createUnit(type, unitId, armor, baseDamage, experience, sanctified);
            addUnitToArmy(civilization, unit, type);
        }
    }

    private void loadSpecialUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM special_units_stats WHERE civilization_id = ?"+ id);
    
        for (Map<String, Object> unitData : query) {
            String type = (String) unitData.get("type");
            int unitId = (int) unitData.get("unit_id");
            int armor = (int) unitData.get("armor");
            int baseDamage = (int) unitData.get("base_damage");
            int experience = (int) unitData.get("experience");
    
            MilitaryUnit unit = createUnit(type, unitId, armor, baseDamage, experience, false);
            addUnitToArmy(civilization, unit, type);
        }
    }
    
    private MilitaryUnit createUnit(String type, int unitId, int armor, int baseDamage, int experience, boolean sanctified) {
        try {
            Class<?> unitClass = Class.forName(type);
            return (MilitaryUnit) unitClass.getConstructor(int.class, int.class, int.class, int.class, boolean.class)
                    .newInstance(unitId, armor, baseDamage, experience, sanctified);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public void addUnitToArmy(Civilization civilization, MilitaryUnit unit, String type) {
        if (unit == null) return;

        switch (type) {
            case "Swordsman":
                civilization.army.get(civilization.swordsman_index).add(unit);
                break;
            case "Spearman":
                civilization.army.get(civilization.spearman_index).add(unit);
                break;
            case "Crossbow":
                civilization.army.get(civilization.crossbow_index).add(unit);
                break;
            case "Cannon":
                civilization.army.get(civilization.cannon_index).add(unit);
                break;
            case "ArrowTower":
                civilization.army.get(civilization.arrow_tower_index).add(unit);
                break;
            case "Catapult":
                civilization.army.get(civilization.catapult_index).add(unit);
                break;
            case "RocketLauncherTower":
                civilization.army.get(civilization.rocket_launcher_index).add(unit);
                break;
            case "Magician":
                civilization.army.get(civilization.magician_index).add(unit);
                break;
            case "Priest":
                civilization.army.get(civilization.priest_index).add(unit);
                break;
        }
    }

    public String[] getCargarPartidaContent(){
        AppData db = AppData.getInstance();

        List<Map<String, Object>> nombres = db.query("SELECT civilization_id, name FROM Civilization_stats");
        String[] listaNombres = new String[nombres.size()];
        for(int i = 0; i<nombres.size(); i++){
            listaNombres[i] = nombres.get(i).get("civilization_id")+". "+nombres.get(i).get("name");
        }
        return listaNombres;
    }

    public Civilization load(int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM Civilization_stats WHERE civilization_id = "+id);

        if (query.isEmpty()) {
            return null; // No se encontró la civilización
        }

        Map<String, Object> infoCivilization = query.get(0);

        Civilization civilization = new Civilization((String) infoCivilization.get("name"));
        civilization.wood = (int) infoCivilization.get("wood_amount");
        civilization.iron = (int) infoCivilization.get("iron_amount");
        civilization.food = (int) infoCivilization.get("food_amount");
        civilization.mana = (int) infoCivilization.get("mana_amount");
        civilization.magicTower = (int) infoCivilization.get("magicTower_counter");
        civilization.church = (int) infoCivilization.get("church_counter");
        civilization.farm = (int) infoCivilization.get("farm_counter");
        civilization.smithy = (int) infoCivilization.get("smithy_counter");
        civilization.carpentry = (int) infoCivilization.get("carpentry_counter");
        civilization.technologyDefense = (int) infoCivilization.get("technology_defense_level");
        civilization.technologyAttack = (int) infoCivilization.get("technology_attack_level");
        civilization.battles = (int) infoCivilization.get("battles_counter");

        loadUnits(civilization, id);

        return civilization;
    }

    public BigDecimal addCivilization(String name) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> resultado = db.query("SELECT * FROM Civilization_stats where name = '" + name + "'");
        if (!resultado.isEmpty()) {
            return new BigDecimal("-1");
        } else {
            String sql = "INSERT INTO Civilization_stats (name, wood_amount, iron_amount, food_amount, mana_amount, magicTower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) VALUES ('" + name + "', 10000, 10000, 10000, 10000, 0, 0, 0, 0, 0, 0, 0, 0)";
            db.update(sql);
            db.update("COMMIT");
        }
        List<Map<String, Object>> query = db.query("SELECT * FROM Civilization_stats where name = '" + name + "'");
        System.out.println(query);
        BigDecimal civilization_id = (BigDecimal) query.get(0).get("CIVILIZATION_ID");
        return civilization_id;
    }

    public void delCivlizations(){
        AppData db = AppData.getInstance();
        String sql = "DELETE FROM CIVILIZATION_STATS";
        db.update(sql);
        db.update("COMMIT");
    }

    public BigDecimal getIdByName(String name) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM Civilization_stats where name = '" + name + "'");
        BigDecimal civilization_id = (BigDecimal) query.get(0).get("CIVILIZATION_ID");
        return civilization_id;
    }
}
