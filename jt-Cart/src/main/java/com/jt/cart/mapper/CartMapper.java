package com.jt.cart.mapper;

import com.jt.cart.pojo.TbCart;
import com.jt.common.mapper.SysMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CartMapper extends SysMapper<TbCart> {
    @Select("select * from tb_cart where user_id=#{userId} and item_id=#{itemId}")
    TbCart findByUseridItemid(TbCart cart);

    void CartUpdate(TbCart tbCart);
@Delete("delete from tb_cart where user_id=#{userId} and item_id=#{itemId}")
    void deleteCart(@Param("userId") Long userId,@Param("itemId") Long itemId);
}
