package com.mao.service;

import com.mao.NotFoundException;
import com.mao.dao.BlogRepository;
import com.mao.dto.BlogQuery;
import com.mao.po.Blog;
import com.mao.po.Type;
import com.mao.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 15:59
 * Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Blog getBlog(Integer id) {
        return blogRepository.getById(id);
    }

    /**
     * 根据查询数据进行分页
     * @param pageable
     * @param blogQuery
     * @return
     */
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {
        return blogRepository.findAll(new Specification<Blog>() {
            /**
             *
             * @param root 将 blog 对象映射成一个 root
             * @param criteriaQuery 查询条件
             * @param criteriaBuilder 设置条件表达式
             * @return
             */
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!"".equals(blogQuery.getTitle()) && null != blogQuery.getTitle()){
                    predicates.add(criteriaBuilder.like(root.get("title"),"%" + blogQuery.getTitle() + "%"));
                }
                if(blogQuery.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blogQuery.getTypeId()));
                }
                if(blogQuery.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blogQuery.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    /**
     * 直接分页
     * @param pageable
     * @return
     */
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Override
    public List<Blog> listBlogRecommendBlog(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTopRecommend(pageable);
    }

    @Transactional
    public Blog saveBlog(Blog blog) {
        blog.setView(0);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        return blogRepository.save(blog);
    }

    @Transactional
    public Blog updateBlog(Integer id, Blog blog) {
        Blog b = blogRepository.getById(id);
        if(b == null){
            throw new NotFoundException("该博客不存在");
        }
        // 复制属性，同时去掉 blog 里面为空的属性
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }
}
