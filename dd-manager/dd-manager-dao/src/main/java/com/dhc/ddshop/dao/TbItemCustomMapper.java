package com.dhc.ddshop.dao;

import com.dhc.ddshop.common.dto.Order;
import com.dhc.ddshop.common.dto.Page;
import com.dhc.ddshop.pojo.vo.TbItemCustom;
import com.dhc.ddshop.pojo.vo.TbItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemCustomMapper {

    int countItems(@Param("tbItemQuery")TbItemQuery query);

    List<TbItemCustom> listItemsByPage(@Param("page")Page page, @Param("order")Order order,@Param("tbItemQuery")TbItemQuery query);

}
