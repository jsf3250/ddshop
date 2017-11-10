package com.dhc.ddshop.service.impl;

import com.dhc.ddshop.common.dto.Order;
import com.dhc.ddshop.common.dto.Page;
import com.dhc.ddshop.common.dto.Result;
import com.dhc.ddshop.dao.TbItemCustomMapper;
import com.dhc.ddshop.dao.TbItemMapper;
import com.dhc.ddshop.pojo.po.TbItem;
import com.dhc.ddshop.pojo.po.TbItemExample;
import com.dhc.ddshop.pojo.vo.TbItemCustom;
import com.dhc.ddshop.pojo.vo.TbItemQuery;
import com.dhc.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomMapper;

    @Override
    public TbItem getById(Long itemId) {


        return itemDao.selectByPrimaryKey(itemId);
    }

    @Override
    public List<TbItem> listItems() {


        return itemDao.selectByExample(null);
    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {

        Result result = new Result();
        int total = itemCustomMapper.countItems(query);
        result.setTotal(total);

        List<TbItemCustom> list = itemCustomMapper.listItemsByPage(page,order,query);
        result.setRows(list);

        return result;
    }

    @Override
    public int updateBatch1(List<Long> ids) {
        int i = 0;
        TbItem record = new TbItem();
        record.setStatus((byte)3);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        i = itemDao.updateByExampleSelective(record,example);

        return i;
    }

    @Override
    public int updateBatch2(List<Long> ids) {
        int i = 0;
        TbItem record = new TbItem();
        record.setStatus((byte)1);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        i = itemDao.updateByExampleSelective(record,example);

        return i;
    }

    @Override
    public int updateBatch3(List<Long> ids) {
        int i = 0;
        TbItem record = new TbItem();
        record.setStatus((byte)2);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        i = itemDao.updateByExampleSelective(record,example);

        return i;
    }



}
