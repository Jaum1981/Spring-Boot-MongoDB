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
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
	    Optional<User> user = userRepository.findById(id);
	    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insertUser(User userObj) {
		return userRepository.insert(userObj);
	}

	// nao foi implementado em UserDTO pois User já está "linkado" com o Banco de Dados, que dependendo da situação pode ser mais complicado sua manutenção
	public User fromDTO (UserDTO userDTOObj) {
		return new User(userDTOObj.getId(), userDTOObj.getName(), userDTOObj.getEmail());
	}

	public void deleteUserById(String id) {
		userRepository.deleteById(id);
	}
}