package com.example.mongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.example.mongodb.domain.Post;
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
		List<User> listUsers = userService.findAllUsers();
		//Conversão de listUsers para listUsersDTO ------- Expressão lambda ---- caminho de volta(stream para lista)
		List<UserDTO> listUsersDTO = listUsers.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUsersDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findUserById(id);
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

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTOObj, @PathVariable String id) {
		User userObj = userService.fromDTO(userDTOObj);
		userObj.setId(id); //garantir que o id do objeto seja o mesmo da requisição
		userObj = userService.updateUser(userObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userObj.getId()).toUri();
		return  ResponseEntity.noContent().build();
	}

	// retorna os post de determinado usuário
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findUserPosts(@PathVariable String id){
		User userObj = userService.findUserById(id);
		return ResponseEntity.ok().body(userObj.getPosts());
	}

}
