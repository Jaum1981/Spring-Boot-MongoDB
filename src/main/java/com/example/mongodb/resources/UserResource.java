package com.example.mongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mongodb.domain.User;
import com.example.mongodb.dto.UserDTO;
import com.example.mongodb.services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> listUsers = userService.findAll();
		//Conversão de listUsers para listUsersDTO ------- Expressão lambda ---- caminho de volta(stream para lista)
		List<UserDTO> listUsersDTO = listUsers.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUsersDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@PostMapping
	public ResponseEntity<Void> insertUser(@RequestBody UserDTO userDTOObj){
		User userObj = userService.fromDTO(userDTOObj);
		userObj = userService.insertUser(userObj);
		//retornar o cabeçalho do novo recurso criado (boa prática)
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userObj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //resposta vazia com cod 201
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable String id){
		userService.deleteUserById(id);
		return ResponseEntity.noContent().build();
	}

}
