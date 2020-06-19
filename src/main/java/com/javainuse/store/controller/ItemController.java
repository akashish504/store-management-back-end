package com.javainuse.store.controller;
import com.javainuse.store.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javainuse.store.dtos.Item;
import com.javainuse.store.dtos.ItemDto;
import com.javainuse.store.services.InventoryManipulationService;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;


    private InventoryManipulationService inventoryManipulationService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/item_list", method = RequestMethod.GET)
    public @ResponseBody Iterable<Item> listPage() {
        return itemRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/add_item", method = RequestMethod.POST)
    public @ResponseBody String addItem(@RequestBody ItemDto item) {
        Item check_item = itemRepository.findByItemName(item.getItemName());
        if (check_item == null) {
            Item new_item = new Item();
            new_item.setQuantity(item.getQuantity());
            new_item.setPrice(item.getPrice());
            new_item.setItemName(item.getItemName());
            itemRepository.save(new_item);
            return "Item Saved";
        }
        else {
            Item old_item = new Item();
            old_item.setId(itemRepository.findByItemName(item.getItemName()).getId());
            old_item.setItemName(item.getItemName());
            old_item.setPrice(item.getPrice());
            old_item.setQuantity(itemRepository.findByItemName(item.getItemName()).getQuantity() + item.getQuantity());
            itemRepository.save(old_item);
            return "Inventory updated";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/subtract_items", method = RequestMethod.POST)
    public @ResponseBody String subtractItem(@RequestBody ItemDto item) {
        Item old_item = new Item();
        old_item.setId(itemRepository.findByItemName(item.getItemName()).getId());
        old_item.setItemName(item.getItemName());
        old_item.setPrice(itemRepository.findByItemName(item.getItemName()).getPrice());
        old_item.setQuantity(itemRepository.findByItemName(item.getItemName()).getQuantity() - item.getQuantity());
        itemRepository.save(old_item);
        return "Inventory Updated";
    }
}

