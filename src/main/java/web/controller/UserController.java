package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    private String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("users/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("newUser")
    public String saveUser(User user) {
        return "adduser";
    }

    @PostMapping("/newUser")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        } else {
            userService.updateUser(user);
            return "redirect:/users";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/updateUser/{id}/update")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "update";
    }

    @PatchMapping("/updateUser/{id}")
    public String update(@ModelAttribute("user") @Valid User updateUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        } else {
            userService.updateUser(updateUser);
            return "redirect:/users";
        }
    }
}
