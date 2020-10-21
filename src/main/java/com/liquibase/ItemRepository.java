package com.liquibase;

import java.util.Optional;

import org.springframework.data.repository.Repository;


/**
 * @author Julius Krah
 */
public interface ItemRepository extends Repository<Item, Integer> {
    /**
     * Save a given Item for a house
     * 
     * @param item must not be null
     * @return the saved item
     */
    Item save(Item item);

    /**
     * Retrieves an Item by it's id
     * 
     * @param id must not be null
     * @return the Item with the given id or empty
     */
    Optional<Item> findById(Integer id);
}
