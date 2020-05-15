package com.sj.bookit.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void creation() {
        Category category = Category.builder().name("Novice").build();

        assertThat(category.getName()).isEqualTo("Novice");
    }

}