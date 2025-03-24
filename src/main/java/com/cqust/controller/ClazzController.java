package com.cqust.controller;

import com.cqust.pojo.Clazz;
import com.cqust.pojo.ClazzQueryParam;
import com.cqust.pojo.PageResult;
import com.cqust.pojo.Result;
import com.cqust.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级管理Controller
 */
@RequestMapping("/clazzs")
@RestController
@Slf4j
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询全部班级信息 /clazzs?
     */
    @GetMapping
    public Result getClazzInfo(ClazzQueryParam param) {
        PageResult<Clazz> pageResult = clazzService.page(param);
        return Result.success(pageResult);
    }

    /**
     * 根据ID删除班级  clazzs/1
     */
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id) {
        log.info("根据id删除班级，id：{}", id);
        clazzService.DeleteById(id);
        return Result.success();
    }

    /**
     * 添加班级信息 json
     */
    @PostMapping
    public Result saveClazz(@RequestBody Clazz clazz) {
        log.info("添加班级信息，clazz：{}", clazz);
        clazzService.saveClazz(clazz);
        return Result.success();
    }

    /**
     * 根据ID查询班级信息 clazzs/1
     */
    @GetMapping("/{id}")
    public Result GetClazzInfoById(@PathVariable Integer id) {
        log.info("根据id查询班级信息，id：{}", id);
        Clazz clazz = clazzService.getClazzById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息 clazzs/1
     */
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("修改班级信息，clazz：{}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result getClazzList() {
        log.info("查询所有班级信息");
        return Result.success(clazzService.getClazzList());
    }
}
