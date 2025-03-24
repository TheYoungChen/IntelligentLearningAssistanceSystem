package com.cqust.service;

import com.cqust.pojo.StudentQueryParam;
import com.cqust.pojo.PageResult;
import com.cqust.pojo.Student;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询全部学生信息
     */
    PageResult<Student> page(StudentQueryParam param);

    /**
     * 删除学生信息
     */
    void deleteStudent(List<Integer> ids);

    /**
     * 添加学生
     */
    void addStudent(Student student);

    /**
     * 根据id查询学生信息
     */
    Student getStudentById(Integer id);

    /**
     * 修改学生信息
     */
    void updateStudent(Student student);

    /***
     * 添加违规记录
     */
    void violationRecord(Integer id, Integer score);
}
