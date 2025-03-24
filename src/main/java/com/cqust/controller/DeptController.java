package com.cqust.controller;

import com.cqust.anno.Log;
import com.cqust.pojo.Dept;
import com.cqust.pojo.Result;
import com.cqust.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    // RequestMapping的衍生注解
    @GetMapping
    public Result list() {
        System.out.println("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门：方式一 HttpServletRequest
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("删除部门的id为：" + id);
//        return Result.success();
//    }

    /**
     * 删除部门：方式二 注解@RequestParam
     * 注意事项：一旦声明了@RequestParam注解 该参数在请求时必须传递 否则会报错（除非将required设置为false）
     */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam("id") Integer id) {
        System.out.println("删除部门的id为：" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 删除部门：方式三 省略注解@RequestParam（前端传递的请求参数名与形参名一致时）
     */
//    @DeleteMapping("/depts")
//    public Result delete2(Integer id) {
//        System.out.println("删除部门的id为：" + id);
//        return Result.success();
//    }

    /**
     * 添加部门
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        System.out.println("添加部门的信息为：" + dept);
        deptService.addDept(dept);
        return Result.success();
    }

    /**
     * 查询部门
     */
//    @GetMapping("/depts/{id}")
//    public Result findById(@PathVariable("id") Integer id) {
//        System.out.println("查询部门的id为：" + id);
//        return Result.success();
//    }

    /**
     * 查询部门
     */
    @GetMapping("/{id}")
    public Result findById2(@PathVariable Integer id) {
        System.out.println("查询部门的id为：" + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门的信息为：" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
