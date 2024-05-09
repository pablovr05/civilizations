CREATE TABLE Battle_stats (
    num_battle INTEGER,
    civilization_id INTEGER NOT NULL,
    wood_acquired INTEGER,
    iron_acquired INTEGER,
    CONSTRAINT fk_civilization_id_battle FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT pk_battle_stats PRIMARY KEY (num_battle, civilization_id)
);

CREATE TABLE Civilization_attack_stats (
    civilization_id INTEGER NOT NULL,
    num_battle INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    initials INTEGER,
    drops INTEGER,
    CONSTRAINT fk_civilization_attack_stats FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT fk_num_battle_attack_civilization_attack_stats FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    CONSTRAINT pk_civiliztion_attack_stats PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Civilization_defense_stats (
    civilization_id INTEGER NOT NULL,
    num_battle INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('ArrowTower','Catapult','RocketLauncherTower')),
    initials INTEGER,
    drops INTEGER,
    CONSTRAINT fk_civilization_defense_stats FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT fk_num_battle_defense_civilization_defense_stats FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    CONSTRAINT pk_civilization_defense_stats PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Civilization_special_stats (
    civilization_id INTEGER NOT NULL,
    num_battle INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Magician','Priest')),
    initials INTEGER,
    drops INTEGER,
    CONSTRAINT fk_civilization_special_stats FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT fk_num_battle_special_civilization_special_stats FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    CONSTRAINT pk_civilization_special_stats PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Enemy_attack_stats (
    civilization_id INTEGER NOT NULL,
    num_battle INTEGER NOT NULL,
    type VARCHAR2(20) CHECK (type IN ('Swordsman','Spearman','Crossbow','Cannon')),
    initials INTEGER,
    drops INTEGER,
    CONSTRAINT fk_enemy_attack_stats FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT fk_num_battle_attack_enemy_attack_stats FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    CONSTRAINT pk_enemy_attack_stats PRIMARY KEY (type, civilization_id, num_battle)
);

CREATE TABLE Battle_log (
    civilization_id INTEGER NOT NULL,
    num_battle INTEGER NOT NULL,
    num_line INTEGER NOT NULL,
    log_entry VARCHAR2(255),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id),
    PRIMARY KEY (civilization_id, num_battle, num_line)
);
