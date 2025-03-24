package com.cqust.mapper;

import com.cqust.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门数据
     */
    // 方式一手动封装映射
//    @Results({
//        @Result(column = "create_time", property = "createTime"),
//        @Result(column = "update_time", property = "updateTime")
//    })

    // 方式二起别名
    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    /**
     * 根据id删除部门
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insertDept(Dept dept);

    /**
     * 根据id查询部门
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 更新部门
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
