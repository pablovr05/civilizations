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

/*
lista -> [[["atacante_civilization", "defensor_enemy", "ataque", "defensa", "repetir"],["atacante_enemy", "defensor_civilization", ataque, defensa, repetir]], ...]

La lista grande es todo el log, cada lista dentro de esta es un turno, y cada turno tiene una lista que es el ataque de la civilizacion y el ataque del enemigo.

Cada ataque se compone de 5 Strings: El nombre del atacante, el nombre del defensor, el numero de ataque, el número de defensa y el boleano de si se repite el ataque.
*/



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
    civilization_id INTEGER NOT NULL,
    wood_acquired INTEGER,
    iron_acquired INTEGER,
    winner NUMBER(1,0),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    PRIMARY KEY (num_battle, civilization_id)
);

CREATE TABLE Enemy_attack_stats (
    num_battle INTEGER NOT NULL,
    civilization_id INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Civilization_attack_stats (
    num_battle INTEGER NOT NULL,
    civilization_id INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Civilization_defense_stats (
    num_battle INTEGER NOT NULL,
    civilization_id INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('ArrowTower','Catapult','RocketLauncherTower')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Civilization_special_stats (
    num_battle INTEGER NOT NULL,
    civilization_id INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Magician','Priest')),
    initials INTEGER,
    drops INTEGER,
    FOREIGN KEY ( civilization_id ) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Battle_log (
    civilization_id INTEGER NOT NULL,
    num_battle INTEGER NOT NULL,
    num_line INTEGER NOT NULL,
    atacante_civilization VARCHAR2(20),
    defensor_enemy VARCHAR2(20),
    ataque_civilization INTEGER,
    defensa_enemy INTEGER,
    repite_civilization NUMBER(1,0),
    atacante_enemy VARCHAR2(20),
    defensor_civilization VARCHAR2(20),
    ataque_enemy INTEGER,
    defensa_civilization INTEGER,
    repite_enemy NUMBER(1,0),
    FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    PRIMARY KEY (num_battle, civilization_id, num_line)
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
END unit_id_defense_trigger;

CREATE OR REPLACE TRIGGER unit_id_special_trigger
BEFORE INSERT ON special_units_stats
FOR EACH ROW
BEGIN
    SELECT unit_id_special_seq.NEXTVAL INTO :NEW.unit_id FROM dual;
END unit_id_special_trigger;

CREATE OR REPLACE TRIGGER battle_num_trigger
BEFORE INSERT ON Battle_stats
FOR EACH ROW
BEGIN
    SELECT num_battle_seq.NEXTVAL INTO :NEW.num_battle FROM DUAL;
END battle_num_trigger;

CREATE OR REPLACE TRIGGER num_line_trigger
BEFORE INSERT ON Battle_log
FOR EACH ROW
BEGIN
    SELECT num_line_seq.NEXTVAL INTO :NEW.num_battle FROM DUAL;
END;
*/



