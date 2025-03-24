package com.cqust.mapper;

import com.cqust.pojo.Emp;
import com.cqust.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     */
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();

    /**
     * 分页查询
     */
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    public List<Emp> list(EmpQueryParam param);

    /**
     * 新增员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
//    @Insert("insert into emp (username, name, gender, image, job, entry_date, dept_id, create_time, update_time) " +
//            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据ID批量删除员工的基本信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工信息以及工作经历信息
     */
    Emp getById(Integer id);

    /**
     * 根据ID更新员工的基本信息
     */
    void updateById(Emp emp);

    /**
     * 统计各职位人数
     */
    @MapKey("position")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计各性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 登录
     */
    Emp selectByUsernameAndPassword(Emp emp);
}
