package com.mao.service.Impl;

import com.mao.NotFoundException;
import com.mao.dao.TagRepository;
import com.mao.dao.TypeRepository;
import com.mao.po.Tag;
import com.mao.po.Type;
import com.mao.service.TagService;
import com.mao.util.IdsConvertToList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 11:10
 * Description:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
    @Transactional
    @Override
    public Tag getTag(Integer id) {
        return tagRepository.getById(id);
    }
    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTopSizeBlog(pageable);
    }

    /**
     * 通过一组 ids ，找到对应的 tag 集合
     * @param ids
     * @return
     */
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(IdsConvertToList.convertToList(ids));
    }

    @Transactional
    @Override
    public Tag updateTag(Integer id, Tag tag) {
        Tag t = tagRepository.getById(id);
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteTag(Integer id) {
        tagRepository.deleteById(id);
    }
}
