package com.sj.bookit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TypeItem> typeItems;

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
