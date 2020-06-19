package com.javainuse.store.repo;

import com.javainuse.store.dtos.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByItemName(String itemName);
}
