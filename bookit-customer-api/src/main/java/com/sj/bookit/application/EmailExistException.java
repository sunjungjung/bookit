package com.sj.bookit.application;

public class EmailExistException extends RuntimeException{

    EmailExistException(String email) {
        super("Email is already registered: " + email);
    }

}
