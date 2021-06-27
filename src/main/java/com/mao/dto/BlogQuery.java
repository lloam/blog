package com.mao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Administrator
 * Date: 2021/6/26 17:49
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private String title;
    private Integer typeId;
    private boolean recommend;
}
