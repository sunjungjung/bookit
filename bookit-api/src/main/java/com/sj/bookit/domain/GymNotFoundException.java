package com.sj.bookit.domain;

public class GymNotFoundException extends RuntimeException {


    public GymNotFoundException(long id) {
        super("Could not find gym " + id);
    }
}
