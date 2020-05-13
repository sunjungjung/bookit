package com.sj.bookit.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gym {
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    private String name;

    private String address;

//    private String regionName; //Seoul
//    private String categoryName;
//    private String tagNames;

    @Transient
    private List<TypeItem> typeItems = new ArrayList<TypeItem>();


    public Gym(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Gym(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


    public String getInformation() {
        return name + " in " + address;
    }



    public void setTypeItems(List<TypeItem> typeItems) {

        this.typeItems = new ArrayList<>(typeItems);

    }


    public void updateInformation(String name, String address) {

        this.name = name;
        this.address = address;

    }

}
