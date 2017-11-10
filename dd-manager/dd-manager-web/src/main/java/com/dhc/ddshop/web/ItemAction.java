package com.dhc.ddshop.web;


import com.dhc.ddshop.common.dto.Order;
import com.dhc.ddshop.common.dto.Page;
import com.dhc.ddshop.common.dto.Result;
import com.dhc.ddshop.pojo.po.TbItem;
import com.dhc.ddshop.pojo.vo.TbItemCustom;
import com.dhc.ddshop.pojo.vo.TbItemQuery;
import com.dhc.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
public class ItemAction {
    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") Long itemId) {

        return itemService.getById(itemId);
    }


    /*@ResponseBody
    @RequestMapping("/items")
    public List<TbItem> listItems(){

        return itemService.listItems();
    }*/

    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItemCustom> listItemsByPage(TbItemQuery query, Page page, Order order) {

        Result<TbItemCustom> list = null;

        list = itemService.listItemsByPage(page,order,query);
        return list;
    }


    @ResponseBody
    @RequestMapping("/items/batch")
    public int updateBatch1(@RequestParam("ids[]") List<Long> ids) {
        int i = 0;
        i = itemService.updateBatch1(ids);
        System.out.println("i="+i);
        return i;
    }
    @ResponseBody
    @RequestMapping("/items/putaway")
    public int updateBatch2(@RequestParam("ids[]") List<Long> ids) {
        int i = 0;
        i = itemService.updateBatch2(ids);
        System.out.println("i="+i);
        return i;
    }
    @ResponseBody
    @RequestMapping("/items/soldout")
    public int updateBatch3(@RequestParam("ids[]") List<Long> ids) {
        int i = 0;
        i = itemService.updateBatch3(ids);
        System.out.println("i="+i);
        return i;
    }
}
