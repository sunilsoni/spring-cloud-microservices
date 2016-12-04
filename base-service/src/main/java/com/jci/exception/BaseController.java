/**
 * 
 */
package com.jci.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import com.jci.dto.ErrorResponse;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {
	
	@ExceptionHandler (value = {EntityNotFoundException.class})
	public @ResponseBody
	ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex,HttpServletResponse response, HttpServletRequest request, WebRequest webRequest) throws IOException {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return errorResponse;
	}
	

	@ExceptionHandler (value = {IllegalArgumentException.class})
	public @ResponseBody ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex, 
			HttpServletResponse response, HttpServletRequest request, WebRequest webRequest) throws IOException {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return errorResponse;
	}
	
	
	@ExceptionHandler(value=Exception.class)
	public @ResponseBody ErrorResponse handleException(Exception ex, HttpServletResponse response, HttpServletRequest request, WebRequest webRequest) throws IOException{
		ErrorResponse res=new ErrorResponse();
		res.setErrorMessage(ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return res;
	}
	
	@ExceptionHandler(value=DuplicateKeyException.class)
	public @ResponseBody ErrorResponse handleDuplicateKeyException(DuplicateKeyException ex, HttpServletResponse response, HttpServletRequest request, WebRequest webRequest) throws IOException{
		ErrorResponse res=new ErrorResponse();
		res.setErrorMessage(ex.getMessage());
		response.setStatus(HttpStatus.CONFLICT.value());
		return res;
	}
}
