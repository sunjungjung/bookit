package com.sj.bookit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TypeItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long gymId;

    private final String name;

    public TypeItem(String name) {
        
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
