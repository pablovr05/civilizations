CREATE DATABASE IF NOT EXISTS civilizationsdb;
Use civilizationsdb;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Civilization_stats;
DROP TABLE IF EXISTS attack_units_stats;
DROP TABLE IF EXISTS defense_units_stats;
DROP TABLE IF EXISTS special_units_stats;

CREATE TABLE IF NOT EXISTS Civilization_stats (

civilization_id INTEGER PRIMARY KEY AUTO_INCREMENT,

name TEXT,

wood_amount INTEGER,
iron_amount INTEGER,
food_amount INTEGER,
mana_amount INTEGER,

magicTower_counter INTEGER,
church_counter INTEGER,
farm_counter INTEGER,
smithy_counter INTEGER,
carpentry_counter INTEGER,

technology_defense_level INTEGER,
technology_attack_level INTEGER,
battles_counter INTEGER
);

CREATE TABLE IF NOT EXISTS attack_units_stats(
unit_id INTEGER PRIMARY KEY AUTO_INCREMENT,
civilization_id INTEGER,

type ENUM("Swordsman","Spearman","Crossbow","Cannon"),
armor INTEGER,
base_damage INTEGER,
experience INTEGER,
sanctified BOOLEAN,
FOREIGN KEY(civilization_id) REFERENCES Civilization_stats(civilization_id)
);

CREATE TABLE IF NOT EXISTS defense_units_stats(
unit_id INTEGER PRIMARY KEY AUTO_INCREMENT,
civilization_id INTEGER,

type ENUM("ArrowTower","Catapult","RocketLauncherTower"),
armor INTEGER,
base_damage INTEGER,
experience INTEGER,
sanctified BOOLEAN,
FOREIGN KEY(civilization_id) REFERENCES Civilization_stats(civilization_id)
);

CREATE TABLE IF NOT EXISTS special_units_stats(
unit_id INTEGER PRIMARY KEY AUTO_INCREMENT,
civilization_id INTEGER,

type ENUM("Magician","Priest"),
armor INTEGER,
base_damage INTEGER,
experience INTEGER,
FOREIGN KEY(civilization_id) REFERENCES Civilization_stats(civilization_id)
);

