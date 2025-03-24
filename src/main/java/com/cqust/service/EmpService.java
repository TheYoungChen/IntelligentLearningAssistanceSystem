package com.cqust.service;

import com.cqust.pojo.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize);

//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam param);

    /**
     * 新增员工
     */
    void save(Emp emp);

    /**
     * 批量删除员工信息
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工信息
     */
    void update(Emp emp);

    /**
     * 登录
     */
    LoginInfo login(Emp emp);
}
