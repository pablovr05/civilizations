DROP TABLE attack_units_stats;
DROP TABLE defense_units_stats;
DROP TABLE special_units_stats;
DROP TABLE Civilization_stats;

DROP SEQUENCE civilization_id_seq;
DROP SEQUENCE unit_id_attack_seq;
DROP SEQUENCE unit_id_defense_seq;
DROP SEQUENCE unit_id_special_seq;

CREATE TABLE Civilization_stats (
    civilization_id INTEGER PRIMARY KEY,
    name VARCHAR2(100),
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

CREATE TABLE attack_units_stats (
    unit_id INTEGER PRIMARY KEY,
    civilization_id INTEGER,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    sanctified NUMBER(1,0),
    CONSTRAINT fk_civilization_id FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);

CREATE TABLE defense_units_stats (
    unit_id INTEGER PRIMARY KEY,
    civilization_id INTEGER,
    type VARCHAR2(50) CHECK (type IN ('ArrowTower','Catapult','RocketLauncherTower')),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    sanctified NUMBER(1,0),
    CONSTRAINT fk_civilization_id_defense FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);

CREATE TABLE special_units_stats (
    unit_id INTEGER PRIMARY KEY,
    civilization_id INTEGER,
    type VARCHAR2(50) CHECK (type IN ('Magician','Priest')),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    CONSTRAINT fk_civilization_id_special FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);

CREATE SEQUENCE civilization_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE unit_id_attack_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE unit_id_defense_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE unit_id_special_seq START WITH 1 INCREMENT BY 1;

/*
CREATE TRIGGER civilization_id_trigger
BEFORE INSERT ON Civilization_stats
FOR EACH ROW
BEGIN
    SELECT civilization_id_seq.NEXTVAL INTO :NEW.civilization_id FROM dual;
END civilization_id_trigger;

CREATE TRIGGER attack_units_trigger
BEFORE INSERT ON attack_units_stats
FOR EACH ROW
BEGIN
    SELECT unit_id_attack_seq.NEXTVAL INTO :NEW.unit_id FROM dual;
END attack_units_trigger;

CREATE OR REPLACE TRIGGER unit_id_defense_trigger
BEFORE INSERT ON defense_units_stats
FOR EACH ROW
BEGIN
    SELECT unit_id_defense_seq.NEXTVAL INTO :NEW.unit_id FROM dual;
END;

CREATE OR REPLACE TRIGGER unit_id_special_trigger
BEFORE INSERT ON special_units_stats
FOR EACH ROW
BEGIN
    SELECT unit_id_special_seq.NEXTVAL INTO :NEW.unit_id FROM dual;
END;
*/