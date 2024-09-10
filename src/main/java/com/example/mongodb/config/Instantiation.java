package com.example.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import com.example.mongodb.domain.User;
import com.example.mongodb.dto.AuthorDTO;
import com.example.mongodb.dto.CommentDTO;
import com.example.mongodb.repositories.UserRepository;
import com.example.mongodb.domain.Post;
import com.example.mongodb.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		//limpa do mongodb
		userRepository.deleteAll();
		postRepository.deleteAll();

		//pré-instanciação usuários
		User maria = new User(null, "maria brown", "mariabrown@email.com");
		User joao = new User(null, "joao victor", "joaovictor@email.com");
		User bob = new User(null, "bob", "bobbob@email.com");

		//salva usuários logo após instanciar
		userRepository.saveAll(Arrays.asList(maria, joao, bob));

		//pré-instanciação de posts
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Vou viajar para São Paulo. Abraços!",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Olá!", "São Paulo é incrível!", new AuthorDTO(maria));
		Post post3 = new Post(null, sdf.parse("22/03/2018"), "Passei!", "Consegui uma vaga de estágio! Estou muito feliz :)", new AuthorDTO(joao));
		Post post4 = new Post(null, sdf.parse("23/03/2018"), "Acabou", "Não tankei o semestre", new AuthorDTO(bob));

//		//pré-instanciação de comments
		CommentDTO comment1 = new CommentDTO("Boa viagem mano.", sdf.parse("21/03/2018"), new AuthorDTO(bob));
		CommentDTO comment2 = new CommentDTO("Aproveite", sdf.parse("09/09/2024"), new AuthorDTO(joao));
		CommentDTO comment3 = new CommentDTO("Parabéns", sdf.parse("2203/09/2018"), new AuthorDTO(maria));

		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post3.getComments().addAll(Arrays.asList(comment3));

		//salva posts logo após instanciar
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		joao.getPosts().addAll(Arrays.asList(post3));
		bob.getPosts().addAll(Arrays.asList(post4));

		//salva novamente, mas agora com os posts referenciados
		userRepository.saveAll(Arrays.asList(maria, joao, bob));

	}

}
