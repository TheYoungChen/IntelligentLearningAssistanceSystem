package com.cqust.service.impl;

import com.cqust.mapper.EmpExprMapper;
import com.cqust.mapper.EmpMapper;
import com.cqust.pojo.*;
import com.cqust.service.EmpService;
import com.cqust.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam param) {
        // 1.设置分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());

        // 2.执行查询
        List<Emp> empList = empMapper.list(param);

        // 3.解析查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Emp emp) {
        // 1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //2 .保存员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            // 遍历集合 为empId赋值
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public void delete(List<Integer> ids) {
        // 1.批量删除员工基本信息
        empMapper.deleteByIds(ids);

        // 2.批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);

    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 2.根据ID修改员工的工作经历信息
        // 2.1 先删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        // 2.2 再添加新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        // 1.调用Mapper接口
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        // 2.判断是否存在员工 若存在 组装登录成功信息
        if (e != null) {
            log.info("登录成功,员工信息：{}", e);
            // 生成Jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId()); // 必须要村
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }
        // 3.若不存在 返回null
        return null;
    }

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        // 1.设置分页参数
//        PageHelper.startPage(page, pageSize);
//
//        // 2.执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//
//        // 3.解析查询结果并封装
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        // 1.调用Mapper接口查询总记录数
//        Long total = empMapper.count();
//
//        // 2.调用Mapper接口查询结果列表
//        // 起始页码,每页记录数
//        List<Emp> rows = empMapper.list((page - 1) * pageSize, pageSize);
//
//        // 3.封装到PageResult对象中并返回
//        return new PageResult<Emp>(total, rows);
//    }


}
