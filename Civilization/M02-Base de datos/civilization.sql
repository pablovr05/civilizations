DROP TABLE attack_units_stats;
DROP TABLE defense_units_stats;
DROP TABLE special_units_stats;

DROP TABLE Enemy_attack_stats;
DROP TABLE Civilization_attack_stats;
DROP TABLE Civilization_defense_stats;
DROP TABLE Civilization_special_stats;
DROP TABLE Battle_log;
DROP TABLE Battle_stats;

DROP TABLE Civilization_stats;

DROP SEQUENCE civilization_id_seq;
DROP SEQUENCE unit_id_attack_seq;
DROP SEQUENCE unit_id_defense_seq;
DROP SEQUENCE unit_id_special_seq;
DROP SEQUENCE num_battle_seq;
DROP SEQUENCE num_line_seq;

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
    unit_id INTEGER NOT NULL,
    civilization_id INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    sanctified NUMBER(1,0),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    PRIMARY KEY (unit_id, civilization_id)
);

CREATE TABLE defense_units_stats (
    unit_id INTEGER NOT NULL,
    civilization_id INTEGER NOT NULL,
    type VARCHAR2(50) CHECK (type IN ('ArrowTower','Catapult','RocketLauncherTower')),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    sanctified NUMBER(1,0),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    PRIMARY KEY (unit_id, civilization_id)
);

CREATE TABLE special_units_stats (
    unit_id INTEGER NOT NULL,
    civilization_id INTEGER NOT NULL,
    type VARCHAR2(50) CHECK (type IN ('Magician','Priest')),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    PRIMARY KEY (unit_id, civilization_id)
);

CREATE TABLE Battle_stats (
    num_battle INTEGER NOT NULL,
    civilization_id_attack INTEGER NOT NULL,
    wood_acquired INTEGER,
    iron_acquired INTEGER,
    FOREIGN KEY (civilization_id_attack) REFERENCES Civilization_stats(civilization_id),
    PRIMARY KEY (num_battle, civilization_id_attack)
);

CREATE TABLE Enemy_attack_stats (
    num_battle INTEGER NOT NULL,
    civilization_id_attack INTEGER NOT NULL,
    civilization_id_defense INTEGER,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id_defense ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id_attack) REFERENCES Battle_stats(num_battle, civilization_id_attack),
    PRIMARY KEY (type, civilization_id_attack, num_battle)
);

CREATE TABLE Civilization_attack_stats (
    num_battle INTEGER NOT NULL,
    civilization_id_attack INTEGER NOT NULL,
    civilization_id_defense INTEGER,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id_defense ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id_attack) REFERENCES Battle_stats(num_battle, civilization_id_attack),
    PRIMARY KEY (type, civilization_id_attack, num_battle)
);

CREATE TABLE Civilization_defense_stats (
    num_battle INTEGER NOT NULL,
    civilization_id_attack INTEGER NOT NULL,
    civilization_id_defense INTEGER,
    type VARCHAR2(20) CHECK (type IN ('ArrowTower','Catapult','RocketLauncherTower')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id_defense ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id_attack) REFERENCES Battle_stats(num_battle, civilization_id_attack),
    PRIMARY KEY (type, civilization_id_attack, num_battle)
);

CREATE TABLE Civilization_special_stats (
    num_battle INTEGER NOT NULL,
    civilization_id_attack INTEGER NOT NULL,
    civilization_id_defense INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Magician','Priest')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id_defense ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id_attack) REFERENCES Battle_stats(num_battle, civilization_id_attack),
    PRIMARY KEY (type, civilization_id_attack, num_battle)
);

CREATE TABLE Battle_log (
    civilization_id_attack INTEGER NOT NULL,
    num_battle INTEGER NOT NULL,
    num_line INTEGER NOT NULL,
    log_entry VARCHAR2(255),
    FOREIGN KEY (num_battle, civilization_id_attack) REFERENCES Battle_stats(num_battle, civilization_id_attack),
    PRIMARY KEY (num_battle, civilization_id_attack, num_line)
);

CREATE SEQUENCE civilization_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE unit_id_attack_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE unit_id_defense_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE unit_id_special_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE num_battle_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE num_line_seq START WITH 1 INCREMENT BY 1;

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

CREATE OR REPLACE TRIGGER battle_num_trigger
BEFORE INSERT ON Battle_stats
FOR EACH ROW
BEGIN
    SELECT num_battle_seq.NEXTVAL INTO :NEW.num_battle FROM DUAL;
END;

CREATE OR REPLACE TRIGGER num_line_trigger
BEFORE INSERT ON Battle_log
FOR EACH ROW
BEGIN
    SELECT num_line_seq.NEXTVAL INTO :NEW.num_battle FROM DUAL;
END;
*/



