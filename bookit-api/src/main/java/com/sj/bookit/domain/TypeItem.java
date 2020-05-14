package com.sj.bookit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeItem {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long gymId;

    private String name;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;
}
