package com.mao.service;

import com.mao.po.Tag;
import com.mao.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 14:43
 * Description:
 */
public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Integer id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Integer id,Tag tag);

    void deleteTag(Integer id);
}
