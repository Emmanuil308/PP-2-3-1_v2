package com.kirillplaksin.controller;

import com.kirillplaksin.entity.User;
import com.kirillplaksin.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ControllerUser {

    private ServiceUser serviceUser;

    @Autowired
    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }


    @GetMapping()
    public String showAllUser (Model model) {

        model.addAttribute("allUsers", serviceUser.getAllUser());

        return "show-all";
    }

    @GetMapping("/addUser")
    public String addNewUser(Model model) {

        User tempUser = new User();
        model.addAttribute("saveOrUpdateUser", tempUser);

        return "user-field";
    }


    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute ("saveOrUpdateUser") User user) {
        serviceUser.saveUser(user);

        return "redirect:/";
    }

    @RequestMapping(value = "updateUser")
    public String updateUser(@ModelAttribute ("id") int id, Model model) {

        model.addAttribute("saveOrUpdateUser", serviceUser.getUserById(id));

        serviceUser.saveUser(serviceUser.getUserById(id));

        return "user-field";
    }

    @RequestMapping(value = "deleteUser")
    public String deleteUser (@ModelAttribute ("id") int id) {

        serviceUser.removeUserById(id);

        return  "redirect:/";
    }
}
