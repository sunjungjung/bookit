package com.sj.bookit.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long gymId;

    @NotEmpty
    private String name;

    @Max(5)
    @Min(0)
    @NotEmpty
    private Integer score;


    @NotEmpty
    private String description;

//    public void setGymId(Long gymId) {
//    }
}
