package com.dhc.ddshop.web;



import com.dhc.ddshop.common.dto.TreeNode;
import com.dhc.ddshop.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class ItemCatAction {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/itemCats")
    @ResponseBody
    public List<TreeNode> listItemCatsByPid(@RequestParam("parentId") Long parentId){
        List<TreeNode> treeNodeList =null;
        try {
            treeNodeList = itemCatService.listItemCatsById(parentId);
        }catch (Exception e){

        }

        return treeNodeList;
    }
}
