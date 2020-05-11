package org.open.covid19.controller;

import lombok.extern.slf4j.Slf4j;
import org.open.covid19.entity.UserEntity;
import org.open.covid19.service.IAnalysisCasesService;
import org.open.covid19.service.ICovid19ApiService;
import org.open.covid19.service.ISetCountries;
import org.open.covid19.service.IUserService;
import org.open.covid19.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@Slf4j
public class UserController {
    @Autowired
    public IUserService userService;
    @Autowired
    private ISetCountries setCountries;
    @Autowired
    private ICovid19ApiService iCovid19ApiService;
    @Autowired
    IAnalysisCasesService analysisCasesService;


    @GetMapping("/user")
    public BaseResponse getUser(@RequestParam(value = "username", defaultValue = "yangshuai") String username) {
        UserEntity user = userService.getUser(username);
        return user == null ? BaseResponse.FAILURE : BaseResponse.success200(user);
    }
    @GetMapping("/allCases")
    public BaseResponse setCountries() {
        iCovid19ApiService.insertAllCases();
        return new BaseResponse(200, "正在执行");

    }
    @GetMapping("/record")
    public BaseResponse AllRecords() {
        long start = System.currentTimeMillis();
        List<Map<String, Integer>> list = analysisCasesService.updateRecordSize2Country();
        long end = System.currentTimeMillis();
        log.debug("/record时间差:{}",end - start);
        return null ==list || list.size() <= 0 ? BaseResponse.SUCCESS : BaseResponse.failure400(list);
    }

    @GetMapping("/optimizer")
    public BaseResponse optimizeAllRecord(){
        int result = analysisCasesService.updateRecordOptimizer();
        return result > 0 ? BaseResponse.SUCCESS : BaseResponse.FAILURE;
    }

    @GetMapping("/beginend")
    public BaseResponse beginEndRecord(){
        long start = System.currentTimeMillis();
        int result = analysisCasesService.updateBeginEnd();
        long end = System.currentTimeMillis();
        log.debug("/beginend时间差:{}",end - start);
        return result > 0 ? BaseResponse.SUCCESS : BaseResponse.FAILURE;
    }
}
