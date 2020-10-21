package com.liquibase;

import java.util.Optional;

import org.springframework.data.repository.Repository;

/**
 * @author Julius Krah
 */
public interface HouseRepository extends Repository<House, Integer> {
    /**
     * Save a given House
     * 
     * @param house must not be null
     * @return the saved house
     */
    House save(House house);

    /**
     * Retrieves a House by it's id
     * 
     * @param id must not be null
     * @return the House with the given id or empty
     */
    Optional<House> findById(Integer id);
}
