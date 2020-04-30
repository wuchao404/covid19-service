package org.open.covid19.controller;

import lombok.extern.slf4j.Slf4j;
import org.open.covid19.entity.UserEntity;
import org.open.covid19.service.ICovid19ApiService;
import org.open.covid19.service.ISetCountries;
import org.open.covid19.service.IUserService;
import org.open.covid19.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController {
    @Autowired
    public IUserService userService;
    @Autowired
    private ISetCountries setCountries;
    @Autowired
    private ICovid19ApiService iCovid19ApiService;


    @GetMapping("/user")
    public BaseResponse getUser(@RequestParam(value = "username", defaultValue = "yangshuai") String username) {
        UserEntity user = userService.getUser(username);
        return user == null ? BaseResponse.FAILURE : BaseResponse.success200(user);
    }
    @GetMapping("/test")
    public BaseResponse setCountries() {
        iCovid19ApiService.insertAllCases();
        return new BaseResponse(200, "正在执行");
    }
}
