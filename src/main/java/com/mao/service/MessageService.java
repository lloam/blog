package com.mao.service;

import com.mao.dto.BlogQuery;
import com.mao.po.Blog;
import com.mao.po.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Author: Administrator
 * Date: 2021/6/26 15:46
 * Description:
 */
public interface MessageService {

    Message addMessage(Message message);

    Page<Message> queryMessage(Pageable pageable);

}
