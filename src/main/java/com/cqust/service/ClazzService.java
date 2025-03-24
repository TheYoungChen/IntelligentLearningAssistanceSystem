package com.cqust.service;

import com.cqust.pojo.Clazz;
import com.cqust.pojo.ClazzQueryParam;
import com.cqust.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 根据分页查询所有班级信息
     */
    PageResult<Clazz> page(ClazzQueryParam param);

    /**
     * 根据ID删除班级信息
     */
    void DeleteById(Integer id);

    /**
     * 保存班级信息
     */
    void saveClazz(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    Clazz getClazzById(Integer id);

    /**
     * 修改班级信息
     */
    void updateClazz(Clazz clazz);

    /**
     * 获取全部班级信息
     */
    List<Clazz> getClazzList();
}
