package com.example.shoppinglistspring.service;


import com.example.shoppinglistspring.model.ShoppingItem;
import com.example.shoppinglistspring.model.ShoppingList;
import com.example.shoppinglistspring.repository.ShoppingListRepository;

public class ShoppingListService {

    private ShoppingListRepository listRepository;

    public ShoppingListService() {
        this.listRepository = new ShoppingListRepository();
    }

    public ShoppingList getShoppingList(String username) {
        ShoppingList list = listRepository.getShoppingList(username);

        if(list == null) {
            //listRepository.createNew(username);
            list = new ShoppingList(username);
        }

        return list;
    }

    public void addShoppingItem(String username, ShoppingItem item) {
        listRepository.addItem(username, item);
    }
}
