package com.scalablescripts.auth.mapper;

import com.scalablescripts.auth.model.Error;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ErrorMapper {
    @Select("SELECT * FROM error WHERE user_id = #{userId}")
    ArrayList<Error> getError(int userId);

    @Insert("INSERT INTO error (errCode, errMessage, user_id) VALUES (#{errCode}, #{errMessage}, #{userId})")
    void insertError(String errCode, String errMessage, int userId);
}
