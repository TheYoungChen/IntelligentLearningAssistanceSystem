package com.cqust.service.impl;

import com.cqust.mapper.StudentMapper;
import com.cqust.pojo.StudentQueryParam;
import com.cqust.pojo.PageResult;
import com.cqust.pojo.Student;
import com.cqust.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询全部学生信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public PageResult<Student> page(StudentQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Student> studentList = studentMapper.list(param);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }


    /**
     * 删除学生信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteStudent(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    /**
     * 添加学生
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    /**
     * 根据ID查询学生信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    /**
     * 修改学生信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudent(student);
    }

    /**
     * 违规记录
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void violationRecord(Integer id, Integer score) {
        studentMapper.violationRecord(id, score);
    }
}

