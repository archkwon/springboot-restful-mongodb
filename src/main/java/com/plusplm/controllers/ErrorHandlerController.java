package com.plusplm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorHandlerController {

	private ErrorHandlerController() {
	}

	@ControllerAdvice
	public static class GlobalExceptionHandler {

		private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		@ExceptionHandler(Exception.class)
		public void handleAll(Exception e) {
			log.error("Unhandled exception occurred", e);
		}
	}

}
