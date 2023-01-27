package com.example.shoppinglistspring.controller;



import com.example.shoppinglistspring.model.ShoppingItem;
import com.example.shoppinglistspring.model.ShoppingList;
import com.example.shoppinglistspring.service.ShoppingListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/shoppingList")
public class ShoppingListController {

    private ShoppingListService listService;

    public ShoppingListController() {
        listService = new ShoppingListService();
    }

    @GetMapping
    protected String showShoppingList(Model model, HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        //resp.setContentType("text/html"); hanteras responseBody
        String username = (String) session.getAttribute("username");


        ShoppingList userShoppingList = listService.getShoppingList(username);


        model.addAttribute("items",userShoppingList.getItemList());


        return "shoppingListPage";
    }

    @PostMapping
    public String addItem(HttpSession session, @ModelAttribute ShoppingItem shoppingItem) {
        String username = (String) session.getAttribute("username");

        listService.addShoppingItem(username, shoppingItem);

        return "redirect:/shoppingList";
    }
}