package com.cqust.mapper;

import com.cqust.pojo.Clazz;
import com.cqust.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 分页查询所有班级信息
     */
    public List<Clazz> list(ClazzQueryParam param);

    /**
     * 根据id删除班级信息
     */
    void DeleteById(Integer id);

    /**
     * 添加班级信息
     */
    void insert(Clazz clazz);

    /**
     * 根据id查询班级信息
     */
    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getClazzList();
}
