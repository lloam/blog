package com.mao.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 20:54
 * Description:将字符串 ids 转化为 一个 list 集合
 * ids : 1,2,3,4
 */
public class IdsConvertToList {
    public static List<Integer> convertToList(String ids){
        List<Integer> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null){
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                list.add(Integer.valueOf(id));
            }
        }
        return list;
    }
}
