package com.javainuse.store.services;

import com.javainuse.store.dtos.ItemDto;
import com.javainuse.store.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.javainuse.store.dtos.Item;

public class InventoryManipulationService {

    @Autowired
    ItemRepository itemRepository;

    public String add_item(ItemDto item) {
        Item new_item = new Item();
        new_item.setQuantity(item.getQuantity());
        new_item.setPrice(item.getPrice());
        new_item.setItemName(item.getItemName());
        itemRepository.save(new_item);
        return "Item Saved";
    }

}
