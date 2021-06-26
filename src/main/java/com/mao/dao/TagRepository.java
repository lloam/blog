package com.mao.dao;

import com.mao.po.Tag;
import com.mao.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Administrator
 * Date: 2021/6/26 11:11
 * Description:
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
}
