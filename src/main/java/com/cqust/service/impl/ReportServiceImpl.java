package com.cqust.service.impl;

import com.cqust.mapper.EmpMapper;
import com.cqust.mapper.StudentMapper;
import com.cqust.pojo.DegreeOption;
import com.cqust.pojo.JobOption;
import com.cqust.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 获取员工岗位数据
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public JobOption getEmpJobData() {
        // 1.调用Mapper接口方法查询数据
        List <Map<String, Object>> list = empMapper.countEmpJobData();

        // 2.组装结果并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("position")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    /**
     * 获取员工性别数据
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /**
     * 获取学生学历数据
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {

        return studentMapper.countStudentDegreeData();
    }

    /**
     * 获取各班级人数
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public DegreeOption getStudentClassData() {
        List<Map<String, Object>> degreeList = studentMapper.countStudentClassData();

        List<Object> clazzList = degreeList.stream().map(dataMap -> dataMap.get("clazzs")).toList();
        List<Object> dataList = degreeList.stream().map(dataMap -> dataMap.get("num")).toList();

        return new DegreeOption(clazzList, dataList);
    }
}
