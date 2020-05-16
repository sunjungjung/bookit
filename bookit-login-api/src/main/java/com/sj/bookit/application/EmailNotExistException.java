package com.sj.bookit.application;

public class EmailNotExistException extends RuntimeException {

    EmailNotExistException (String email) { super("Email is not registered: " + email); }

}
