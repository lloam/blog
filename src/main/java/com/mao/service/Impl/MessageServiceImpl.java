package com.mao.service.Impl;

import com.mao.dao.MessageRepository;
import com.mao.po.Message;
import com.mao.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Author: Administrator
 * Date: 2021/6/29 13:08
 * Description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Transactional
    public Message addMessage(Message message) {
        Integer parentId = message.getParentMessage().getId();
        if(parentId != -1){
            message.setParentMessage(messageRepository.getById(parentId));
        }else {
            message.setParentMessage(null);
        }
        message.setCreateTime(new Date());
        return messageRepository.save(message);
    }

    @Override
    public Page<Message> queryMessage(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

}
