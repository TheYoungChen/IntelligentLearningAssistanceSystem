package com.cqust.mapper;

import com.cqust.pojo.Student;
import com.cqust.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 分页查询全部学生信息
     */
    public List<Student> list(StudentQueryParam param);

    /**
     * 根据ID批量删除员工信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 添加学生
     */
    void insert(Student student);

    /**
     * 根据ID查询学生信息
     */
    Student getStudentById(Integer id);

    /**
     * 修改学生信息
     */
    void updateStudent(Student student);

    /**
     * 添加违规记录
     */
    void violationRecord(Integer id, Integer score);

    /**
     * 统计学生学历数据
     */
    @MapKey("degrees")
    List<Map<String, Object>> countStudentDegreeData();

    @MapKey("clazzs")
    List<Map<String, Object>> countStudentClassData();
}
