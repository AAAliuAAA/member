package com.member.util;

import com.github.pagehelper.PageInfo;
import com.member.entity.Menu;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class EasyUIAdapterUtil {
    public static Map<String,Object> convertDatagridMap(PageInfo pageInfo){
        Map<String,Object> data=new HashMap<>();
        data.put("total",pageInfo.getTotal());
        data.put("rows",pageInfo.getList());
        return data;

    }
    // 转化树形菜单作为标准子节点
    public static List convertMenu(List<Menu> menus){
        //找根节点
        List<Map<String,Object>> result=new ArrayList<>();
        if (menus!=null && menus.size()>0){
            for(Menu menu:menus){
                if("0".equals(menu.getParentNode())){
                    Map<String,Object> node=new HashMap<>();
                    node.put("node",menu.getNode());
                    node.put("text",menu.getName());
                    node.put("url",menu.getUrl());
                    result.add(node);
                    iterator(node,menus);
                }
            }
        }
        return result;
    }

    //递归查找子节点
    private static void iterator(Map<String, Object> parentNode, List<Menu> menus) {
        String node= (String) parentNode.get("node");
        //找到子节点
        for(Menu menu:menus){
            if(node.equals(menu.getParentNode())){
                Map<String,Object> child=new HashMap<>();
                child.put("node",menu.getNode());
                child.put("text",menu.getName());
                child.put("url",menu.getUrl());//为了让根节点没有url属性
                if(!parentNode.containsKey("children")){
                    List<Map<String,Object>> children=new ArrayList<>();
                    parentNode.put("children",children);
                }
                //将子节点放入父节点的children集合中
                ((List<Map<String,Object>>)parentNode.get("children")).add(child);
                //递归
                iterator(child,menus);
            }

        }
    }
}
