package com.critina.eblog.vo;

import com.critina.eblog.entity.UserCollection;
import lombok.Data;

/**
 * @program: eblog
 * @description: 收藏类Vo
 * @author: sunzhen
 * @create: 2020-12-16 14:39
 **/
@Data
public class UserCollectionVo extends UserCollection {

    private String title; //收藏的文章标题

}
