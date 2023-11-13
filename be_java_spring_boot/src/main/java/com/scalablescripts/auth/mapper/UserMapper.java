package com.scalablescripts.auth.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.scalablescripts.auth.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(int id);
}
