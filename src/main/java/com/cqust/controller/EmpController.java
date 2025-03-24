package com.cqust.controller;

import com.cqust.pojo.Emp;
import com.cqust.pojo.EmpQueryParam;
import com.cqust.pojo.PageResult;
import com.cqust.pojo.Result;
import com.cqust.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@RequestMapping("/emps")
@RestController
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam (defaultValue = "10") Integer pageSize) {
////        if (page == null) {
////            page = 1;
////        }
////        if (pageSize == null) {
////            pageSize = 10;
////        }
//        PageResult<Emp> pageResult = empService.page(page, pageSize);
//        return Result.success(pageResult);

//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result page(EmpQueryParam param) {
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }

    /**
     * 新增员工 通过json传递数据
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工 通过URL参数或请求参数传递 emps?ids=1,2,3
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除员工，id：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据ID查询员工信息 通过路径变量传递
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工基本信息和工作经历信息，id：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
