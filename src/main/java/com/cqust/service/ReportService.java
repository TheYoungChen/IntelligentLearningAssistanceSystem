package com.cqust.service;

import com.cqust.pojo.DegreeOption;
import com.cqust.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工各职位人数
     */
    JobOption getEmpJobData();

    /**
     * 统计员工各性别人数
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 统计学生各学历人数
     */
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 统计学生各班级人数
     */
    DegreeOption getStudentClassData();
}
