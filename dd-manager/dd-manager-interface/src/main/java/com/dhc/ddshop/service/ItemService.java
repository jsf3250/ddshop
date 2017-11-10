package com.dhc.ddshop.service;

import com.dhc.ddshop.common.dto.Order;
import com.dhc.ddshop.common.dto.Page;
import com.dhc.ddshop.common.dto.Result;
import com.dhc.ddshop.pojo.po.TbItem;
import com.dhc.ddshop.pojo.vo.TbItemCustom;
import com.dhc.ddshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {

    TbItem getById(Long itemId);

    List<TbItem> listItems();

    Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query);

    int updateBatch1(List<Long> ids);
    int updateBatch2(List<Long> ids);
    int updateBatch3(List<Long> ids);
}
