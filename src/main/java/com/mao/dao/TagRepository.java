package com.mao.dao;

import com.mao.po.Tag;
import com.mao.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 11:11
 * Description:
 */
public interface TagRepository extends JpaRepository<Tag,Integer> {

    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTopSizeBlog(Pageable pageable);
}
