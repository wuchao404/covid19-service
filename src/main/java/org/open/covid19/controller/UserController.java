package org.open.covid19.controller;

import org.open.covid19.entity.UserEntity;
import org.open.covid19.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    public IUserService userService;

    @GetMapping("/user")
    public UserEntity getUser(@RequestParam() String username) {
        return userService.getUser(username);
    }
}
