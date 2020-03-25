package com.douzone.mysite.exception;

public class GuestBookRepositoryException extends RuntimeException{

	
	public GuestBookRepositoryException() {
		super("GuestbookRepositoyException Occurs");
	}
	
	public GuestBookRepositoryException(String Message) {
		super(Message);
	}
}
