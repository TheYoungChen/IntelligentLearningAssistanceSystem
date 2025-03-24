package com.cqust.controller;

import com.cqust.pojo.DegreeOption;
import com.cqust.pojo.JobOption;
import com.cqust.pojo.Result;
import com.cqust.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工各职位人数 柱状图
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计各职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工各性别人数 饼状图
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计各性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计学生学历 饼状图
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("统计学生学历");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

    /**
     * 统计班级人数 柱状图
     */
    @GetMapping("/studentCountData")
    public Result getStudentClassData() {
        log.info("统计班级人数");
        DegreeOption degreeOption = reportService.getStudentClassData();
        return Result.success(degreeOption);
    }
}
