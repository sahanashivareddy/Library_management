package org.paycorp.librarymanagmentsystem.exception;

import org.paycorp.librarymanagmentsystem.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException{

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseStructure handle(DataNotFoundException exception)
	{
		ResponseStructure structure=new ResponseStructure();
		structure.setMessage("data not found");
		structure.setData(null);
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return structure;
	}
}
