package com.example.mongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.mongodb.domain.User;
import com.example.mongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll(); //limpa do mongodb
		
		User maria = new User(null, "maria brown", "mariabrown@email.com");
		User joao = new User(null, "joao victor", "joaovictor@email.com");
		User bob = new User(null, "bob", "bobbob@email.com");
		
		userRepository.saveAll(Arrays.asList(maria, joao, bob));
		
	}

}
