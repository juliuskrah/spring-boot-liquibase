package com.liquibase;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import lombok.RequiredArgsConstructor;

@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
public class HouseRepositoryTest {
    private final HouseRepository houseRepository;
    private final TestEntityManager em;

    @BeforeEach
    void before() {
        var house = new House();
        house.setOwner("Julius Krah");
        house.setFullyPaid(true);
        em.persist(house);
    }

    @Test
    @DisplayName("find house by id")
    void testFindById() {
        var house = houseRepository.findById(1);
        var condition = new Condition<House>(h -> h.isFullyPaid(), "Is fully paid");
        assertThat(house).isPresent();
        assertThat(house).hasValueSatisfying(condition);
    }
}
