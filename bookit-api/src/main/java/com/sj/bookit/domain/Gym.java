package com.sj.bookit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gym {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;

    @Transient
    private List<TypeItem> typeItems = new ArrayList<TypeItem>();

    public Gym() {
    }

    public Gym(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Gym(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setID(long id) {
        this.id = id;
    }

    public Long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation() {
        return name + " in " + address;
    }

    public List<TypeItem> getTypeItems() {
        return typeItems;
    }

    public void addTypeItem(TypeItem typeItem) {
        typeItems.add(typeItem);
    }

    public void setGymItems(List<TypeItem> typeItems) {
        for (TypeItem typeItem : typeItems) {
            addTypeItem((typeItem));
        }
    }
}
