package com.project;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AppData {
    private static AppData instance;
    private Connection conn;

    private AppData() {
        // Connecta al crear la primera instància
        connect();
    }

    // Singleton
    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    private void connect() {
        String url = "jdbc:oracle:thin:@192.168.47.33:1521/orcl"; //IMPORTANTE: Cambiar la ip a la de la máquina, la contraseña y admin siempre es la misma
        String user = "admin";
        String password = "admin";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // Desactiva l'autocommit per permetre control manual de transaccions
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connectant a la base de dades Oracle.");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String sql) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            conn.commit(); // Confirma els canvis
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback(); // Reverteix els canvis en cas d'error
            } catch (SQLException ex) {
                System.out.println("Error en fer rollback.");
                ex.printStackTrace();
            }
        }
    }
    
    public int insertAndGetId(String sql) {
        int generatedId = -1;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Obtenir el primer camp com a ID generat
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en fer rollback.");
                ex.printStackTrace();
            }
        }
        return generatedId;
    }
    
    public List<Map<String, Object>> query(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
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

    public static void main(String[] args) {
        AppData db = AppData.getInstance();
        //Insertar para tipo 'Swordsman'
        String sql = "INSERT INTO attack_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) VALUES (165, 'Swordsman', 5, 10, 100, 0)";
        db.update(sql);
        //Insertar para tipo 'Spearman'
        sql = "INSERT INTO attack_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) VALUES (165, 'Spearman', 4, 8, 80, 1)";
        db.update(sql);
        //Insertar para tipo 'Crossbow'
        sql = "INSERT INTO attack_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) VALUES (165, 'Crossbow', 3, 12, 90, 0)";
        db.update(sql);
        //Insertar para tipo 'Cannon'
        sql = "INSERT INTO attack_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) VALUES (165, 'Cannon', 6, 15, 110, '1')";
        db.update(sql);
        //Insertar para tipo 'ArrowTower'
        sql = "INSERT INTO defense_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) VALUES (165, 'ArrowTower', 7, 9, 120, 0)";
        db.update(sql);
        //Insertar para tipo 'Catapult'
        sql = "INSERT INTO defense_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) VALUES (165, 'Catapult', 6, 14, 100, 0)";
        db.update(sql);
        //Insertar para tipo 'RocketLauncherTower'
        sql = "INSERT INTO defense_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) VALUES (165, 'RocketLauncherTower', 8, 20, 130, 1)";
        db.update(sql);
        //Insertar para tipo 'Magician'
        sql = "INSERT INTO special_units_stats (civilization_id, type, armor, base_damage, experience) VALUES (165, 'Magician', 2, 18, 150)";
        db.update(sql);
        //Insertar para tipo 'Priest'
        sql = "INSERT INTO special_units_stats (civilization_id, type, armor, base_damage, experience) VALUES (165, 'Priest', 3, 5, 200)";
        db.update(sql);
        db.update("COMMIT");
    };
}

