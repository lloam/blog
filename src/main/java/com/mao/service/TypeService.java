package com.mao.service;

import com.mao.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 11:08
 * Description:
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Integer id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Integer id,Type type);

    void deleteType(Integer id);
}
