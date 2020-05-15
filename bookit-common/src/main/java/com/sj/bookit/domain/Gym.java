package com.sj.bookit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Long categoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TypeItem> typeItems;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    public String getInformation() {
        return name + " in " + address;
    }


    public void updateInformation(Long categoryId, String name, String address) {

        this.categoryId = categoryId;
        this.name = name;
        this.address = address;

    }


    public void setTypeItems(List<TypeItem> typeItems) {

        this.typeItems = new ArrayList<>(typeItems);

    }


    public void setReviews(List<Review> asList) {

        this.reviews = new ArrayList<>(reviews);

    }
}
