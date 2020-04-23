package org.open.covid19.controller;

import org.open.covid19.entity.UserEntity;
import org.open.covid19.service.IUserService;
import org.open.covid19.utils.BaseResponse;
import org.open.covid19.utils.response.BaseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    public IUserService userService;

    @GetMapping("/user")
    public BaseResponse getUser(@RequestParam(value = "username", defaultValue = "yangshuai") String username) {
        UserEntity user = userService.getUser(username);
        return user == null ? BaseResponse.FAILURE : BaseResponse.success200(user);
    }
}
