package org.open.covid19.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.ApifyApi;
import org.open.covid19.api.JhuApi;
import org.open.covid19.entity.UserEntity;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.open.covid19.service.*;
import org.open.covid19.service.impl.LoggerApply;
import org.open.covid19.utils.BaseResponse;
import org.open.covid19.utils.file.MultipartFileToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    @Autowired
    JhuApi jhuApi;
    @Autowired
    ApifyApi apifyApi;
    @Autowired
    Covid19ApiMapper covid19ApiMapper;
    @Autowired
    IApifyService iApifyService;
    @Autowired
    LoggerApply loggerApply;

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
    @GetMapping("/casesFromDate")
    public BaseResponse getCasesFromDate(){
        analysisCasesService.insertRecordsFromDate();
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

    /**
     * 上传Excel文件，添加国家中英文对照
     * @param multipartFile
     * @return
     */
    @SneakyThrows
    @PostMapping("/excel")
    public BaseResponse uploadExcel(@RequestParam("file") MultipartFile multipartFile){
        log.debug("接收文件:{}",multipartFile.getName());
        File file = MultipartFileToFile.toFile(multipartFile);
        setCountries.readCnNameFromExcel(file);
        MultipartFileToFile.delteTempFile(file);
        return BaseResponse.SUCCESS;
    }
    /**
     * 上传Excel文件，美国各州中英文对照
     * @param multipartFile
     * @return
     */
    @SneakyThrows
    @PostMapping("/excel4usStates")
    public BaseResponse uploadExcel4usStates(@RequestParam("file") MultipartFile multipartFile){
        log.debug("接收文件:{}",multipartFile.getName());
        File file = MultipartFileToFile.toFile(multipartFile);
        setCountries.readAmericanStatesFromExcel(file);
        MultipartFileToFile.delteTempFile(file);
        return BaseResponse.SUCCESS;
    }

    /**
     * 把缺少病例的国家数据补齐
     * @return`
     */
    @GetMapping("/allIfNoExistInCases")
    public BaseResponse insertRecordsIfNoExist(){
        analysisCasesService.insertRecordsIfNoExist();
        return BaseResponse.SUCCESS;
    }
    /**
     * 插入美国各州历史数据
     * @return`
     */
    @GetMapping("/allAmericanStateCase")
    public BaseResponse insertAllAmericanStateCase(){
        iApifyService.insertAllAmericanStatesCase();        return BaseResponse.SUCCESS;
    }

    @GetMapping("/test1")
    public BaseResponse test1(){
        Object lastCases = apifyApi.getLastCases();
        return BaseResponse.success200(lastCases);
    }

    @GetMapping("/get")
    public BaseResponse test2(){
        loggerApply.requestLastAmericanCases();
        return BaseResponse.SUCCESS;
    }
}
