package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.TbItemMapper;
import com.jt.manage.mapper.TbitemDescMapper;
import com.jt.manage.pojo.TbItem;
import com.jt.manage.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbitemDescMapper tbitemDescMapper;
    public EasyUIResult findItemByPage(int page, int rows) {
        //获取商品记录信息总数
      // int total= tbItemMapper.findItemCount();
        int total = tbItemMapper.selectCount(null);
        /**
         * 分页查询
         * @return
         * 第1页select * from tb_item limit 0,20
         * 第2页select * from tb_item limit 20,20
         * 第3页select * from tb_item limit 40,20
         * 第N页select * from tb_item limit (n-1)*rows,20
         */
       int start =(page-1)*rows;
       List<TbItem> itemList=tbItemMapper.findItemByPage(start,rows);

        return new EasyUIResult(total,itemList);
    }

    public String findItemCatById(Long itemId) {
        return tbItemMapper.findItemCatById(itemId);

    }
//入库
    @Override
    public void saveItem(TbItem item,String desc) {
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        tbItemMapper.insert(item);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(item.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(item.getCreated());
        tbItemDesc.setUpdated(item.getCreated());
        tbitemDescMapper.insert(tbItemDesc);



    }

    @Override
    public void updateitem(TbItem item,String desc) {
        item.setUpdated(new Date());
        tbItemMapper.updateByPrimaryKeySelective(item);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(item.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(item.getUpdated());
        tbitemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
    }

    @Override
    public void updateStatus(Long[] ids, int status) {

        tbItemMapper.updateStatus(ids,status);
    }

    @Override
    public void deleteitem(Long[] ids) {

        tbItemMapper.deleteitem(ids);
        tbitemDescMapper.deleteitem(ids);
    }

    public TbItem findByIds(Long itemId) {

        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public TbItemDesc selectByIds(Long itemId) {
        return tbitemDescMapper.selectByPrimaryKey(itemId);
    }
}
