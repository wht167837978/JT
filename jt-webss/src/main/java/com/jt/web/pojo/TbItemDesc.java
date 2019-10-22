package com.jt.web.pojo;


import com.jt.common.po.BasePojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_item_desc")
public class TbItemDesc extends BasePojo {
    @Id
    private Long itemId;       //商品ID
    private String itemDesc;   //商品详情

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
