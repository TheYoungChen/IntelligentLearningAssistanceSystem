package com.cqust.controller;

import com.cqust.pojo.PageResult;
import com.cqust.pojo.Result;
import com.cqust.pojo.Student;
import com.cqust.pojo.StudentQueryParam;
import com.cqust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
@RestController
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询全部学生信息 /students?page=1
     */
    @GetMapping
    public Result getStudentInfo(StudentQueryParam param) {
        log.info("分页查询全部学生信息,参数:{}", param);
        PageResult<Student> pageResult = studentService.page(param);
        return Result.success(pageResult);
    }

    /**
     * 删除学生信息 /students/{ids}
     */
    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable List<Integer> ids) {
        log.info("根据id批量删除学生信息，ids：{}", ids);
        studentService.deleteStudent(ids);
        return Result.success();
    }

    /**
     * 添加学生 通过json传递数据
     */
    @PostMapping
    public Result saveStudent(@RequestBody Student student) {
        log.info("添加学生，student:{}", student);
        studentService.addStudent(student);
        return Result.success();
    }

    /**
     * 根据ID查询学生信息
     */
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id) {
        log.info("根据id查询学生信息，id:{}", id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     */
    @PutMapping
    public Result updateStudent(@RequestBody Student student) {
        log.info("修改学生信息，student:{}", student);
        studentService.updateStudent(student);
        return Result.success();
    }

    /**
     * 违纪记录
     */
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("新增违纪记录，id:{},score:{}", id, score);
        studentService.violationRecord(id, score);
        return Result.success();
    }
}
