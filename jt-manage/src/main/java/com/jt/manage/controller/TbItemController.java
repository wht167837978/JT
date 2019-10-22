package com.jt.manage.controller;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.TbItem;
import com.jt.manage.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class TbItemController {
    @Autowired
    private TbItemService tbItemService;
    //根据分页实现商品列表查询

    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult findTbItemPage(int page, int rows) {
        return tbItemService.findItemByPage(page, rows);
    }

    //根据商品分类id查询商品分类名称
    @RequestMapping(value = "/cat/queryItemName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String findItemCatByid(Long itemId) {
        return tbItemService.findItemCatById(itemId);
    }

    //实现商品的新增
    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(TbItem item,String desc) {

        try {
            tbItemService.saveItem(item,desc);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品新增失败");
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(TbItem item,String desc) {
        try {
            tbItemService.updateitem(item,desc);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品修改失败");
    }

    //下架
    @RequestMapping("/instock")
    @ResponseBody
    public SysResult Instock(Long[] ids) {
        try {
            int status = 2;
            tbItemService.updateStatus(ids, status);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品下架失败");
    }

    //上架
    @RequestMapping("/reshelf")
    @ResponseBody
    public SysResult reshelf(Long[] ids) {
        try {
            int status = 1;
            tbItemService.updateStatus(ids, status);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品上架架失败");
    }

    //商品删除
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteitem(Long[] ids) {
        try {
            tbItemService.deleteitem(ids);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "删除失败");
    }

}
