package io.github.gaosups.qqi.service;

import io.github.gaosups.qqi.model.Room;
import io.github.gaosups.qqi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

		private final RoomRepository roomRepository;

		@Override
		public void run(String... args) {
		}
}
