package com.sj.bookit.application;

public class PasswordIncorrectException extends RuntimeException {

    PasswordIncorrectException() {
        super("Incorrect Password!!");
    }

}
