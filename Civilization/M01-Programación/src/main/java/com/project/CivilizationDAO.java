package com.project;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import oracle.net.aso.c;

public class CivilizationDAO {

    public static int getLastID(){
        AppData db = AppData.getInstance();

        List<Map<String, Object>> listaID = db.query("SELECT civilization_id FROM Civilization_stats ORDER BY civilization_id DESC LIMIT 1");

        BigDecimal id = (BigDecimal) listaID.get(0).get("CIVILIZATION_ID");
        return id.intValue();
    }

    public static void saveUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();

        // Eliminar las unidades existentes de las tablas
        deleteUnits(id);

        saveAttackUnits(civilization, id);
        saveDefenseUnits(civilization, id);
        saveSpecialUnits(civilization, id);

        System.out.println("UNIDADES DESPUÉS DE GUARDARLAS");

        System.out.println(db.query("Select * from attack_units_stats"));
    }

    public static void deleteUnits(int id) {
        AppData db = AppData.getInstance();

        System.out.println("id: " + id);

        db.update("DELETE FROM attack_units_stats WHERE civilization_id = "+ id);
        db.update("DELETE FROM defense_units_stats WHERE civilization_id = "+ id);
        db.update("DELETE FROM special_units_stats WHERE civilization_id = "+ id);

        db.update("COMMIT");

        System.out.println("UNIDADES DESPUÉS DE BORRAR");

        System.out.println(db.query("Select * from attack_units_stats"));


    }

    public static void saveAttackUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();

        for (MilitaryUnit unit : civilization.army.get(civilization.swordsman_index)) {
            saveUnit(db, unit, "Swordsman", id);
        }
        for (MilitaryUnit unit : civilization.army.get(civilization.spearman_index)) {
            saveUnit(db, unit, "Spearman", id);
        }
        for (MilitaryUnit unit : civilization.army.get(civilization.crossbow_index)) {
            saveUnit(db, unit, "Crossbow", id);
        }
        for (MilitaryUnit unit : civilization.army.get(civilization.cannon_index)) {
            saveUnit(db, unit, "Cannon", id);
        }
        db.update("COMMIT");
    }

    public static void saveDefenseUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();

        for (MilitaryUnit unit : civilization.army.get(civilization.arrow_tower_index)) {
            saveUnit(db, unit, "ArrowTower", id);
        }
        for (MilitaryUnit unit : civilization.army.get(civilization.catapult_index)) {
            saveUnit(db, unit, "Catapult", id);
        }
        for (MilitaryUnit unit : civilization.army.get(civilization.rocket_launcher_index)) {
            saveUnit(db, unit, "RocketLauncherTower", id);
        }
        db.update("COMMIT");
    }

    public static void saveSpecialUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();

        for (MilitaryUnit unit : civilization.army.get(civilization.magician_index)) {
            saveUnit(db, unit, "Magician", id);
        }
        for (MilitaryUnit unit : civilization.army.get(civilization.priest_index)) {
            saveUnit(db, unit, "Priest", id);
        }
        db.update("COMMIT");
    }

    public static void saveUnit(AppData db, MilitaryUnit unit, String type, int id) {
        String table;
        if (unit instanceof AttackUnit) {
            table = "attack_units_stats";
        } else if (unit instanceof DefenseUnit) {
            table = "defense_units_stats";
        } else if (unit instanceof SpecialUnit) {
            table = "special_units_stats";
        } else {
            return; // Tipo de unidad desconocido
        }
    
        String sql;
    if (unit instanceof SpecialUnit) {
        sql = "INSERT INTO " + table + " (civilization_id, type, armor, base_damage, experience) VALUES ("+id+", '"+type+"', "+unit.getActualArmor()+", "+unit.attack()+", "+unit.getExperience()+")";
        db.update(sql);
    } else {
        sql = "INSERT INTO " + table + " (civilization_id, type, armor, base_damage, experience, sanctified) VALUES ("+id+", '"+type+"', "+unit.getActualArmor()+", "+unit.attack()+", "+unit.getExperience()+", "+unit.getSanctified()+")";
        db.update(sql);
    }
    }
    

    public static void save(Civilization civilization){

        AppData db = AppData.getInstance();

        BigDecimal id = getIdByName(civilization.name);

        System.out.println("Guardando datos de la civilización: " + civilization.name + " ID:" + id);
        System.out.println("wood_amount = " + civilization.wood);
        System.out.println("food_amount = " + civilization.food);
        System.out.println("iron_amount = " + civilization.iron);
        System.out.println("mana_amount = " + civilization.mana);
        System.out.println("magicTower_counter = " + civilization.magicTower);
        System.out.println("church_counter = " + civilization.church);
        System.out.println("farm_counter = " + civilization.farm);
        System.out.println("smithy_counter = " + civilization.smithy);
        System.out.println("carpentry_counter = " + civilization.carpentry);
        System.out.println("technology_defense_level = " + civilization.technologyDefense);
        System.out.println("technology_attack_level = " + civilization.technologyAttack);
        System.out.println("battles_counter = " + civilization.battles);

        db.update("UPDATE Civilization_stats SET wood_amount = " + civilization.wood + 
          ", food_amount = " + civilization.food + 
          ", iron_amount = " + civilization.iron + 
          ", mana_amount = " + civilization.mana + 
          ", magicTower_counter = " + civilization.magicTower + 
          ", church_counter = " + civilization.church + 
          ", farm_counter = " + civilization.farm + 
          ", smithy_counter = " + civilization.smithy + 
          ", carpentry_counter = " + civilization.carpentry + 
          ", technology_defense_level = " + civilization.technologyDefense + 
          ", technology_attack_level = " + civilization.technologyAttack + 
          ", battles_counter = " + civilization.battles + 
          " WHERE civilization_id = " + id);
        db.update("COMMIT");

        saveUnits(civilization, id.intValue());

        System.out.println("GUARDADO COMPLETADO");

    }

    public static void loadUnits(Civilization civilization, int id) {
        loadAttackUnits(civilization, id);
        loadDefenseUnits(civilization, id);
        loadSpecialUnits(civilization, id);
    }

    private static void loadAttackUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM attack_units_stats WHERE civilization_id = " + id);
    
        for (Map<String, Object> unitData : query) {

            String type = (String) unitData.get("TYPE");
            BigDecimal unitId = (BigDecimal) unitData.get("UNIT_ID");
            BigDecimal armor = (BigDecimal) unitData.get("ARMOR");
            BigDecimal baseDamage = (BigDecimal) unitData.get("BASE_DAMAGE");
            BigDecimal experience = (BigDecimal) unitData.get("EXPERIENCE");
            BigDecimal sanctified = (BigDecimal) unitData.get("SANCTIFIED");
    
            MilitaryUnit unit = createUnit(type, unitId, armor, baseDamage, experience, sanctified);
            addUnitToArmy(civilization, unit, type);
        }
    }
    
    private static void loadDefenseUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM defense_units_stats WHERE civilization_id = " +id);
    
        for (Map<String, Object> unitData : query) {
            String type = (String) unitData.get("TYPE");
            BigDecimal unitId = (BigDecimal) unitData.get("UNIT_ID");
            BigDecimal armor = (BigDecimal) unitData.get("ARMOR");
            BigDecimal baseDamage = (BigDecimal) unitData.get("BASE_DAMAGE");
            BigDecimal experience = (BigDecimal) unitData.get("EXPERIENCE");
            BigDecimal sanctified = (BigDecimal) unitData.get("SANCTIFIED");
    
            MilitaryUnit unit = createUnit(type, unitId, armor, baseDamage, experience, sanctified);
            addUnitToArmy(civilization, unit, type);
        }
    }

    private static void loadSpecialUnits(Civilization civilization, int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM special_units_stats WHERE civilization_id = "+ id);
    
        for (Map<String, Object> unitData : query) {
            String type = (String) unitData.get("TYPE");
            BigDecimal unitId = (BigDecimal) unitData.get("UNIT_ID");
            BigDecimal armor = (BigDecimal) unitData.get("ARMOR");
            BigDecimal baseDamage = (BigDecimal) unitData.get("BASE_DAMAGE");
            BigDecimal experience = (BigDecimal) unitData.get("EXPERIENCE");
    
            MilitaryUnit unit = createUnit(type, unitId, armor, baseDamage, experience, BigDecimal.valueOf(1));
            addUnitToArmy(civilization, unit, type);
        }
    }
    
    private static MilitaryUnit createUnit(String type, BigDecimal unitId, BigDecimal armor, BigDecimal baseDamage, BigDecimal experience, BigDecimal sanctified) {
        boolean booleanSanctified = false;
        if (sanctified.intValue() == 1) {
            booleanSanctified = true;
        }
    
        int iunitId = unitId.intValue();
        int iarmor = armor.intValue();
        int ibaseDamage = baseDamage.intValue();
        int iexperience = experience.intValue();
        
        if ("Swordsman".equals(type.trim())) {
            Swordsman swordsman = new Swordsman(iunitId, iarmor, ibaseDamage, iexperience, booleanSanctified);
            swordsman.printearBonito();
            return swordsman;
        } else if ("Spearman".equals(type.trim())) {
            Spearman spearman = new Spearman(iunitId, iarmor, ibaseDamage, iexperience, booleanSanctified);
            spearman.printearBonito();
            return spearman;
        } else if ("Crossbow".equals(type.trim())) {
            Crossbow crossbow = new Crossbow(iunitId, iarmor, ibaseDamage, iexperience, booleanSanctified);
            crossbow.printearBonito();
            return crossbow;
        } else if ("Cannon".equals(type.trim())) {
            Cannon cannon = new Cannon(iunitId, iarmor, ibaseDamage, iexperience, booleanSanctified);
            cannon.printearBonito();
            return cannon;
        } else if ("ArrowTower".equals(type.trim())) {
            ArrowTower arrowTower = new ArrowTower(iunitId, iarmor, ibaseDamage, iexperience, booleanSanctified);
            arrowTower.printearBonito();
            return arrowTower;
        } else if ("Catapult".equals(type.trim())) {
            Catapult catapult = new Catapult(iunitId, iarmor, ibaseDamage, iexperience, booleanSanctified);
            catapult.printearBonito();
            return catapult;
        } else if ("RocketLauncherTower".equals(type.trim())) {
            RocketLauncherTower rocketLauncherTower = new RocketLauncherTower(iunitId, iarmor, ibaseDamage, iexperience, booleanSanctified);
            rocketLauncherTower.printearBonito();
            return rocketLauncherTower;
        } else if ("Magician".equals(type.trim())) {
            Magician magician = new Magician(iunitId, iarmor, ibaseDamage, iexperience); 
            magician.printearBonito();
            return magician;
        } else if ("Priest".equals(type.trim())) {
            Priest priest = new Priest(iunitId, iarmor, ibaseDamage, iexperience); 
            priest.printearBonito();
            return priest;
        } else {
            return null;
        }
    }
    
    
    public static void addUnitToArmy(Civilization civilization, MilitaryUnit unit, String type) {
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

    public static String[] getCargarPartidaContent(){
        AppData db = AppData.getInstance();

        List<Map<String, Object>> nombres = db.query("SELECT civilization_id, name FROM Civilization_stats");
        String[] listaNombres = new String[nombres.size()];
        for(int i = 0; i<nombres.size(); i++){
            listaNombres[i] = nombres.get(i).get("NAME").toString();
        }
        return listaNombres;
    }

    public static Civilization load(int id) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM Civilization_stats WHERE civilization_id = "+id);

        if (query.isEmpty()) {
            return null; // No se encontró la civilización
        }

        Map<String, Object> infoCivilization = query.get(0);
        String name = (String) infoCivilization.get("NAME");
        Civilization civilization = new Civilization(name);

        BigDecimal wood_amount = (BigDecimal) infoCivilization.get("WOOD_AMOUNT");
        civilization.setWood(wood_amount.intValue());

        BigDecimal iron_amount = (BigDecimal) infoCivilization.get("IRON_AMOUNT");
        civilization.setIron(iron_amount.intValue());

        BigDecimal food_amount = (BigDecimal) infoCivilization.get("FOOD_AMOUNT"); 
        civilization.setFood(food_amount.intValue());

        BigDecimal mana_amount = (BigDecimal) infoCivilization.get("MANA_AMOUNT");
        civilization.setMana(mana_amount.intValue());

        BigDecimal magic_tower = (BigDecimal) infoCivilization.get("MAGICTOWER_COUNTER");
        civilization.setMagicTower(magic_tower.intValue());

        BigDecimal church = (BigDecimal) infoCivilization.get("CHURCH_COUNTER");
        civilization.setChurch(church.intValue());

        BigDecimal farm = (BigDecimal) infoCivilization.get("FARM_COUNTER");
        civilization.setFarm(farm.intValue());

        BigDecimal smithy = (BigDecimal) infoCivilization.get("SMITHY_COUNTER");
        civilization.setSmithy(smithy.intValue());

        BigDecimal carpentry = (BigDecimal) infoCivilization.get("CARPENTRY_COUNTER");
        civilization.setCarpentry(carpentry.intValue());

        BigDecimal defense_level = (BigDecimal) infoCivilization.get("TECHNOLOGY_DEFENSE_LEVEL");
        civilization.setTechnologyDefense(defense_level.intValue());

        BigDecimal attack_level = (BigDecimal) infoCivilization.get("TECHNOLOGY_ATTACK_LEVEL");
        civilization.setTechnologyAttack(defense_level.intValue());

        BigDecimal battles = (BigDecimal) infoCivilization.get("BATTLES_COUNTER");
        civilization.setBattles(battles.intValue());

        loadUnits(civilization, id);

        return civilization;
    }

    public static BigDecimal addCivilization(String name) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> resultado = db.query("SELECT * FROM Civilization_stats where name = '" + name + "'");
        if (!resultado.isEmpty()) {
            return new BigDecimal("-1");
        } else {
            String sql = "INSERT INTO Civilization_stats (name, wood_amount, iron_amount, food_amount, mana_amount, magicTower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) VALUES ('" + name + "', 5000, 5000, 5000, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
            db.update(sql);
            db.update("COMMIT");
        }
        List<Map<String, Object>> query = db.query("SELECT * FROM Civilization_stats where name = '" + name + "'");
        BigDecimal civilization_id = (BigDecimal) query.get(0).get("CIVILIZATION_ID");
        return civilization_id;
    }

    public static void delCivlizations(){
        AppData db = AppData.getInstance();
        String sql = "DELETE FROM CIVILIZATION_STATS";
        db.update(sql);
        db.update("COMMIT");
    }

    public static BigDecimal getIdByName(String name) {
        AppData db = AppData.getInstance();
        List<Map<String, Object>> query = db.query("SELECT * FROM Civilization_stats where name = '" + name + "'");
        BigDecimal civilization_id = (BigDecimal) query.get(0).get("CIVILIZATION_ID");
        return civilization_id;
    }
}
