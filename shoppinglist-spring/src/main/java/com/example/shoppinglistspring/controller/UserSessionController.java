package com.example.shoppinglistspring.controller;


import com.example.shoppinglistspring.model.AuthDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/userSession/*")
public class UserSessionController {

    @GetMapping("login") //inlog blir usersession/login eftersom inlogg är här. login
        public String showLoginPage(){ //metoden leder=return till loginPage.html
       return "loginPage";
    }


    @PostMapping("login") //POST ÄR DEN SOM SKICKAR TILL FORMULÄRET
    public String login(HttpSession session, RedirectAttributes redirect, @ModelAttribute AuthDetails auth) {

        if(session.getAttribute("username") != null) {
            return "redirect:/shoppingList";
        } else {

             if(auth.getUsername().equals("bob") && auth.getPassword().equals("123")) {
              // if (auth.getUsername() != null) {
                session.setMaxInactiveInterval(60 * 30);
                session.setAttribute("username", auth.getUsername());

                return "redirect:/shoppingList";
            } else {
                Object loginAttempts = session.getAttribute("loginAttempts");
                if(loginAttempts == null) {
                    loginAttempts = 0;
                }

                session.setAttribute("loginAttempts", (int) loginAttempts + 1);
                redirect.addAttribute("error", "Invalid username or password");

                return "redirect:login"; //skicka felmedelande i th
                //return "redirect:login?error=" + error;
            }
        }
    }

    @PostMapping("logout")
    public String logout(HttpSession session) throws IOException {
        session.invalidate(); //Invalidate - empty the session
        return "redirect:/index.html";
    }
}