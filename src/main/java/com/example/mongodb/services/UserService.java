package com.example.mongodb.services;

import java.util.List;
import java.util.Optional;

import com.example.mongodb.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongodb.domain.User;
import com.example.mongodb.repositories.UserRepository;
import com.example.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	//metodo que retorna todos os usuários em formato de lista
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	//metodo que retorna um usuário pelo id
	public User findUserById(String id) {
	    Optional<User> user = userRepository.findById(id);
	    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	//metodo que insere um novo usuário passando um obj do tipo User
	public User insertUser(User userObj) {
		return userRepository.insert(userObj);
	}

	// nao foi implementado em UserDTO pois User já está "linkado" com o Banco de Dados, que dependendo da situação pode ser mais complicado sua manutenção
	public User fromDTO (UserDTO userDTOObj) {
		return new User(userDTOObj.getId(), userDTOObj.getName(), userDTOObj.getEmail());
	}

	// metodo para deletar um usuário existente através do seu id
	public void deleteUserById(String id) {
		findUserById(id);
		userRepository.deleteById(id);
	}

	private void updateData(User newUser, User userObj){
		newUser.setName(userObj.getName());
		newUser.setEmail(userObj.getEmail());
	}

	public User updateUser(User userObj) {
		User newUserObj = findUserById(userObj.getId());
		updateData(newUserObj, userObj);
		return userRepository.save(newUserObj);
	}
}