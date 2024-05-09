CREATE TABLE battle_stats (
    num_battle INTEGER,
    civilization_id INTEGER NOT NULL,
    wood_acquired INTEGER,
    iron_acquired INTEGER,
    CONSTRAINT pk_battle_stats PRIMARY KEY (num_battle, civilization_id),
    CONSTRAINT fk_civilization_id_battle FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);

CREATE SEQUENCE num_battle_seq START WITH 1 INCREMENT BY 1;

CREATE TRIGGER num_battle_trigger
BEFORE INSERT ON battle_stats
FOR EACH ROW
BEGIN
    SELECT num_battle_seq.NEXTVAL INTO :NEW.num_battle FROM dual;
END num_battle_trigger;
