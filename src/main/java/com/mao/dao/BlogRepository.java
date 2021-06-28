package com.mao.dao;

import com.mao.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Modifying
    @Query("update Blog b set b.view = b.view + 1 where b.id = ?1")
    int updateViews(Integer id);

    // 将博客所含的年份查出来
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year")
    List<String> findGroupYears();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    long count();

    @Query("select b from Blog b where b.title = ?1")
    Blog findBlogByTitle(String title);
}
