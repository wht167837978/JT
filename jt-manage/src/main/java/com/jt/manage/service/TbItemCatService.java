package com.jt.manage.service;

import com.jt.manage.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TbItemCatService {


    List<EasyUITree> findTtemCatById(Long parentId);

    List<EasyUITree> findTtemCatRdisById(Long parentId);
}
