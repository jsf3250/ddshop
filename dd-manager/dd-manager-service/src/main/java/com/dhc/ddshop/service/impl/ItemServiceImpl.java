package com.dhc.ddshop.service.impl;

import com.dhc.ddshop.common.dto.Order;
import com.dhc.ddshop.common.dto.Page;
import com.dhc.ddshop.common.dto.Result;
import com.dhc.ddshop.common.util.IDUtils;
import com.dhc.ddshop.dao.TbItemCustomMapper;
import com.dhc.ddshop.dao.TbItemDescMapper;
import com.dhc.ddshop.dao.TbItemMapper;
import com.dhc.ddshop.pojo.po.TbItem;
import com.dhc.ddshop.pojo.po.TbItemDesc;
import com.dhc.ddshop.pojo.po.TbItemExample;
import com.dhc.ddshop.pojo.vo.TbItemCustom;
import com.dhc.ddshop.pojo.vo.TbItemQuery;
import com.dhc.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomMapper;
    @Autowired
    private TbItemDescMapper itemDescDao;

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
    @Transactional
    @Override
    public int saveItem(TbItem tbItem, String content) {
       int i = 0;
        try {
            long itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setUpdated(new Date());
            tbItem.setCreated(new Date());
            tbItem.setStatus((byte)2);
            i = itemDao.insert(tbItem);

            //处理tb_item_desc
            TbItemDesc desc = new TbItemDesc();
            desc.setItemId(itemId);
            desc.setItemDesc(content);
            desc.setCreated(new Date());
            desc.setUpdated(new Date());
            i += itemDescDao.insert(desc);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return i;
    }


}
