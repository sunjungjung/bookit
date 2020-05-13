package com.sj.bookit.domain;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class GymTests {

    @Test
    public void creation() {
        Gym gym = Gym.builder()
                .id(1001L)
                .name("work out")
                .address("Seoul")
                .build();

        assertThat(gym.getId(), is(1001L));
        assertThat(gym.getName(), is("work out"));
        assertThat(gym.getAddress(), is("Seoul"));
    }

    @Test
    public void information() {
        Gym gym = new Gym(1001L, "work out", "Seoul");

        assertThat(gym.getInformation(), is("work out in Seoul"));
    }

}