package com.cqust.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EmpQueryParam {
    private Integer page = 1; // 页码
    private Integer pageSize = 10; // 每页记录数
    private String name; // 姓名
    private Integer gender; // 性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 入职时间-开始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 入职时间-结束

    public EmpQueryParam() {
    }
    public EmpQueryParam(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        this.page = page;
        this.pageSize = pageSize;
        this.name = name;
        this.gender = gender;
        this.begin = begin;
        this.end = end;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
