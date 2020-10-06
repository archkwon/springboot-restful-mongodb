/**
 * 예외처리 자체정의
 * @package com.plusplm.main
 * @file Execute.java
 * @author 권민관
 * @date 2019. 1. 30. 오전 9:37:48
 * @version 1.0 
 * @see
 *
 * <pre>
 
 *   수정일          수정자         수정내용 
 *  -------       --------     --------------------------- 
 *  2019. 1. 30.   권민관         최초 생성 
 * 
 * Copyright (C) 2019 by (주)마린소프트 All right reserved.
 *
 * </pre>
*/
package com.plusplm.exception;

public class ProcessingException extends Exception{

	private static final long serialVersionUID = 1L;
	private int errorCode; 
	private String message;

	public ProcessingException(String message, int errorCode){
		super(message);
		this.setErrorCode(errorCode);
		this.setMessage(message);
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
