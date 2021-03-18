package com.rbc.itemsonsale.repository;

import com.rbc.itemsonsale.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<User, Integer> {
    public User findUserByUserId(int id);
}
