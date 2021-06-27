package com.mao.dao;

import com.mao.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 15:59
 * Description:
 */
public interface BlogRepository extends JpaRepository<Blog,Integer>, JpaSpecificationExecutor<Blog> {

    /**
     * 查找推荐的博客
     * @return
     */
    @Query("select b from Blog b where b.recommend = true ")
    List<Blog> findTopRecommend(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);
}
