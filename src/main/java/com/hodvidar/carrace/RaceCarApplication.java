package com.hodvidar.carrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootApplication
public class RaceCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaceCarApplication.class, args);
		log.warn("RaceCar app run! " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")));
	}

}
