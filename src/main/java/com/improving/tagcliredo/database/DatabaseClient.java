package com.improving.tagcliredo.database;


import com.improving.tagcliredo.models.Emote;
import com.improving.tagcliredo.models.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class DatabaseClient {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseClient.class);
    private final JdbcTemplate jdbcTemplate;

    public DatabaseClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void readDataFromTable() {
        try {
           List<String> names = jdbcTemplate.query("SELECT * From Weapon LIMIT 10", (result, rowNum) -> result.getString("Name"));
        } catch (DataAccessException e) {
            logger.error("Error: ", e);
        }
    }
    public void insertIntoTableWeapon() {
        try {
            logger.info("GOT IT!");
            int rowsAffected = jdbcTemplate.update("INSERT INTO weapon (Name, Area, ItemType) VALUES ('SMALLISH DAGGER', 'Dagger shop', 'Weapon')");
            logger.info("Rows Affected: {}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC: ", e);
        }
    }
    public List<Weapon> readWeaponsFromTable() {
        try {
            List<Weapon> weapons = jdbcTemplate.query("Select * From weapon LIMIT 10", (result, rowNum) ->
                    new Weapon(result.getInt("Id"), result.getString("Name"), result.getString("Area"), result.getString("ItemType")));
            weapons.forEach(weapon -> logger.info("Weapon Id: {}, Name: {}", weapon.getId(), weapon.getName()));
            return weapons;
        } catch (DataAccessException e) {
            logger.error("Error: ", e);
        }
        return Collections.EMPTY_LIST;
    }

    public void insertIntoTableEmote(Emote emote) {
        try {
            logger.info("GOT Emote!");
            int rowsAffected = jdbcTemplate.update("INSERT INTO emote (Prompt, Text) VALUES ('" + emote.getPrompt() + "', '" + emote.getText() + "')");
            logger.info("Rows Affected: {}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC: ", e);
        }
    }
}
