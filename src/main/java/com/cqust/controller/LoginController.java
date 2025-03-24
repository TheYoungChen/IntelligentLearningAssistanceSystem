package com.cqust.controller;

import com.cqust.pojo.Emp;
import com.cqust.pojo.LoginInfo;
import com.cqust.pojo.Result;
import com.cqust.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录：{}", emp);
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo != null) {
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }
}
