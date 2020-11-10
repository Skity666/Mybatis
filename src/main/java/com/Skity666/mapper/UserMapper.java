package com.Skity666.mapper;

import com.Skity666.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
    *@author Skity666
    *@Description mybatis接口类
    *@Date 19:22 2020/11/9 0009
    *@Param
    *@return
    **/
public interface UserMapper {

    List<User> findAll();

    User findById(Long id);

    int insert(User user);

    List<User> findByFilter(@Param("filter") Map<String,Object> filter);

    List<User> findByFilter(@Param("filter") User filter);

    List<User> findByIds(@Param("ids") List<Long> ids);
}
