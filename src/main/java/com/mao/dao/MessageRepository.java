package com.mao.dao;

import com.mao.po.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Administrator
 * Date: 2021/6/29 13:07
 * Description:
 */
public interface MessageRepository extends JpaRepository<Message,Integer> {
}
