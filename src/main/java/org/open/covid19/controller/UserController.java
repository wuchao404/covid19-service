package org.open.covid19.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.Covid19Api;
import org.open.covid19.entity.Country;
import org.open.covid19.entity.UserEntity;
import org.open.covid19.service.IUserService;
import org.open.covid19.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
public class UserController {
    @Autowired
    public IUserService userService;
    @Autowired
    private Covid19Api covid19Api;

    @GetMapping("/user")
    @Deprecated
    public BaseResponse getUser(@RequestParam(value = "username", defaultValue = "yangshuai") String username) {
        UserEntity user = userService.getUser(username);
        return user == null ? BaseResponse.FAILURE : BaseResponse.success200(user);
    }
    @SneakyThrows
    @GetMapping("/set-country")
    public BaseResponse setCountries() {
        List<Country> countries = covid19Api.getCountries();
        log.debug("countries[0]:{}", countries.get(0));
        return BaseResponse.success200(countries);
    }
}
