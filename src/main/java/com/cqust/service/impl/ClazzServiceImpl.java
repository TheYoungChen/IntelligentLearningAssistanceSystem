package com.cqust.service.impl;

import com.cqust.mapper.ClazzMapper;
import com.cqust.pojo.Clazz;
import com.cqust.pojo.ClazzQueryParam;
import com.cqust.pojo.PageResult;
import com.cqust.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询班级信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public PageResult<Clazz> page(ClazzQueryParam param) {
        // 1.设置分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());

        // 2.执行查询
        List<Clazz> clazzList = clazzMapper.list(param);

        // 3.解析查询结果并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    /**
     * 根据ID删除班级信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void DeleteById(Integer id) {
        clazzMapper.DeleteById(id);
    }

    /**
     * 添加班级信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void saveClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据ID查询班级信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Clazz getClazzById(Integer id) {
        return clazzMapper.getClazzById(id);
    }

    /**
     * 修改班级信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<Clazz> getClazzList() {
        return clazzMapper.getClazzList();
    }

}
