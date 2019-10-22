package com.jt.manage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.manage.mapper.TbitemCatMapper;
import com.jt.manage.pojo.TbItemCat;
import com.jt.manage.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    private static ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    private TbitemCatMapper tbitemCatMapper;
    @Autowired
    private ShardedJedisPool shardedJedisPool;


    public List<EasyUITree> findTtemCatById(Long parentId) {
        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setParentId(parentId);
        List<TbItemCat> itemCatlist = tbitemCatMapper.select(tbItemCat);
        List<EasyUITree> treelist = new ArrayList<EasyUITree>();
        for (TbItemCat itemCatTemp : itemCatlist) {
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(itemCatTemp.getId());
            easyUITree.setText(itemCatTemp.getName());
            String state = itemCatTemp.getParent() ? "closed" : "open";
            easyUITree.setState(state);
            treelist.add(easyUITree);

        }
        return treelist;
    }

    @Override
    public List<EasyUITree> findTtemCatRdisById(Long parentId) {
        ShardedJedis Jedis = shardedJedisPool.getResource();
        List<EasyUITree>  easyUITree=null;
        String key="Item_Cat_Redis"+parentId;
        String json = Jedis.get(key);

            try {
                if(StringUtils.isEmpty(json)){
                    List<EasyUITree> ttemCatById = findTtemCatById(parentId);
                    String jsoData = objectMapper.writeValueAsString(ttemCatById);
                    Jedis.set(key, jsoData);
                    System.out.println("库查");
                    shardedJedisPool.returnResource(Jedis);


                }else {
                    EasyUITree[] tree = objectMapper.readValue(json, EasyUITree[].class);
                    easyUITree= Arrays.asList(tree);
                    System.out.println("缓存");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        return easyUITree;
    }
}
