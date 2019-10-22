package com.jt.sso.mapper;


import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.UUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper  extends SysMapper<UUser> {
    int findCheckUser(@Param("cloumn")String cloumn,@Param("param")String param);
@Select("select * from tb_user where username=#{username} and password=#{password}")
    UUser findUserByUp(UUser user);
}
