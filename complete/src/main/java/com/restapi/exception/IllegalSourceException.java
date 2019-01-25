package com.restapi.exception;

public class IllegalSourceException extends Exception {

	  private static final long serialVersionUID = 1L;

	  protected int number = 0;
	  protected String description = "";

	  public IllegalSourceException(String message) {
	    super(message);
	    // addCode("CallerID.invalid");
	  }

	  public IllegalSourceException(String message, Throwable rootCause) {
	    super(message, rootCause);
	    // addCode("CallerID.invalid");
	  }

	  public void setNumber(int number) {
	    this.number = number;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }
}