package com.jt.manage.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Param;

public interface TbitemDescMapper extends SysMapper<TbItemDesc> {
    void deleteitem(@Param("ids") Long[] ids);
}
