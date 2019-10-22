package com.jt.manage.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.TbItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemMapper extends SysMapper<TbItem> {
    /**
     * 查询总记录数
     * @return
     */
  //  @Select("select Count(*) from tb_item")
   // int findItemCount();

    /**
     * 分页查询
     * @return
     * 第1页select * from tb_item limit 0,20
     * 第2页select * from tb_item limit 20,20
     * 第3页select * from tb_item limit 40,20
     * 第N页select * from tb_item limit (n-1)*rows,20
     */
    List<TbItem> findItemByPage(@Param("start") int start,@Param("rows") int rows);

@Select("select name from tb_item_cat where id=#{itemId}")
   String findItemCatById(@Param("itemId") Long itemId);

    void updateStatus(@Param("ids") Long[] ids,@Param("status") int status);

    void deleteitem(@Param("ids") Long[] ids);
}
