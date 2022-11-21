package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private static UserService userService = new UserServiceImpl();

    @Autowired
    public UserController(UserService userService) {
        UserController.userService = userService;
    }

    @GetMapping()
    private String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/getUser";
    }

    @GetMapping("/newUser")
    public String addUser(User user) {
        return "users/adduser";
    }

    @PostMapping("/newUser")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/adduser";
        } else {
            userService.updateUser(user);
            return "users:/";
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "users:/";
    }

    @GetMapping("updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "users/update";
    }

    @PatchMapping("/updateUser")
    public String update(@ModelAttribute("user") @Valid User updateUser, BindingResult bingres) {
        if (bingres.hasErrors()) {
            return "users/update";
        } else {
            userService.updateUser(updateUser);
            return "users:/";
        }
    }
}
