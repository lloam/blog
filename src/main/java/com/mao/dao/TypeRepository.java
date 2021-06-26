package com.mao.dao;

import com.mao.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Administrator
 * Date: 2021/6/26 11:11
 * Description:
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
