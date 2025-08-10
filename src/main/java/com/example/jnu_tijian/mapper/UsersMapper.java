package com.example.jnu_tijian.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.example.jnu_tijian.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UsersMapper {
    List<Users> getAll();


//        Users login(@Param("userId") String userId, @Param("password") String password);

        Users login(Users user);


    int deleteByPrimaryKey(String userId);

    int insert(Users record);

    int insertSelective(Users record);

    /**  根据手机号查询用户  */
    Users selectByPrimaryKey(String userId);

    int register(Users user);


    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

}