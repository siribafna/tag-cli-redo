package com.improving.tagcliredo;

import com.improving.tagcliredo.database.DatabaseClient;
import com.improving.tagcliredo.database.OldSchoolDatabaseClient;
import com.improving.tagcliredo.models.Emote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;
import java.util.Scanner;

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
//		databaseClient.insertIntoTableWeapon();
//		databaseClient.readWeaponsFromTable();

		Scanner input = new Scanner(System.in);

		//int choice = input.nextInt();
		boolean loop = true;
		while (loop) {
			System.out.println("Do you want to [1] Add Data, [2] Read Data");
			int choice = input.nextInt();
			if (choice == 1) {
				System.out.println("Would you like to [1] Add Emote, [2] Add Weapon");
				choice = input.nextInt();
				if (choice == 1) {
					System.out.println("What do you want your prompt to be?");
					String prompt = input.next();
					System.out.println("What do you want your text to be?");
					String text = input.next();
					Emote emote = new Emote(prompt, text);
					databaseClient.insertIntoTableEmote(emote);
				}
				if (choice == 2)
					databaseClient.insertIntoTableWeapon();
				} else
					System.out.println("Invalid Input, Try again");
		}
	}
}