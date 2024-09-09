package com.example.mongodb.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.mongodb.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //indica que essa classe é responsavel por tratar erros
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) //padrao do framework
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

		HttpStatus status = HttpStatus.NOT_FOUND;
		//									 pega o istante atual do sistema---converte para inteiro -- msg --- tipo de erro --- caminho que gerou a exceção
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
