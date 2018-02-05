package org.taljaard.nextgear.exceptions.advisor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.taljaard.nextgear.exceptions.BeanCopyException;
import org.taljaard.nextgear.exceptions.ContractDataInvalidException;
import org.taljaard.nextgear.exceptions.NoDataFoundException;
import org.taljaard.nextgear.exceptions.PersistanceDeleteException;
import org.taljaard.nextgear.exceptions.PersistanceSaveException;
import org.taljaard.nextgear.exceptions.PersistanceUpdateException;
import org.taljaard.nextgear.exceptions.dto.ExceptionDTO;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ContractDataInvalidException.class)
	@ResponseBody
	public ResponseEntity<Object> handleContractDataInvalidException(ContractDataInvalidException ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<Object>(exceptionDTO, new HttpHeaders(), exceptionDTO.getStatus());
	}
	
	@ExceptionHandler(BeanCopyException.class)
	@ResponseBody
	public ResponseEntity<Object> handleContractBeanCopyException(BeanCopyException ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<Object>(exceptionDTO, new HttpHeaders(), exceptionDTO.getStatus());
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseBody
	public ResponseEntity<Object> handleContractNoDataFoundException(NoDataFoundException ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<Object>(exceptionDTO, new HttpHeaders(), exceptionDTO.getStatus());
	}
	
	@ExceptionHandler(PersistanceDeleteException.class)
	@ResponseBody
	public ResponseEntity<Object> handlePersistanceDeleteException(PersistanceDeleteException ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<Object>(exceptionDTO, new HttpHeaders(), exceptionDTO.getStatus());
	}
	
	@ExceptionHandler(PersistanceSaveException.class)
	@ResponseBody
	public ResponseEntity<Object> handlePersistanceSaveException(PersistanceSaveException ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<Object>(exceptionDTO, new HttpHeaders(), exceptionDTO.getStatus());
	}
	
	@ExceptionHandler(PersistanceUpdateException.class)
	@ResponseBody
	public ResponseEntity<Object> handlePersistanceUpdateException(PersistanceUpdateException ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<Object>(exceptionDTO, new HttpHeaders(), exceptionDTO.getStatus());
	}
	
}
