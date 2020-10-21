package com.liquibase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Julius Krah
 */
@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(HouseRepository houseRepository, ItemRepository itemRepository) {
		return args -> {
			log.info("Saving house...");
			var house = new House();
			house.setOwner("Julius Krah");
			house.setFullyPaid(true);
			houseRepository.save(house);
			log.info("Saved house instance: {}", house);

			log.info("Saving item in house");
			var item = new Item();
			item.setName("Washing machine");
			item.setHouse(house);
			itemRepository.save(item);
			log.info("Saved item {} in house", item);
		};
	}

}
