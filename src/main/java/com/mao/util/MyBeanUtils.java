package com.mao.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/27 13:54
 * Description: 转换blog时去掉 null 值
 */
public class MyBeanUtils {

    /**
     * 获取所有属性值为空的属性名数组
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source){
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if(beanWrapper.getPropertyValue(propertyName) == null){
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
