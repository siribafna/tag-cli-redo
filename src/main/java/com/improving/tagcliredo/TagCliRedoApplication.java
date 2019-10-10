package com.improving.tagcliredo;

import com.improving.tagcliredo.database.DatabaseClient;
import com.improving.tagcliredo.database.OldSchoolDatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;

@SpringBootApplication
public class TagCliRedoApplication implements CommandLineRunner {

	private final DatabaseClient databaseClient;

	public TagCliRedoApplication(DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}
	public static void main(String[] args) {
		SpringApplication.run(TagCliRedoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databaseClient.insertIntoTable();
		databaseClient.readWeaponsFromTable();
	}
}
