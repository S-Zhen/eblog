package com.critina.eblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @program: eblog
 * @description:
 * @author: sunzhen
 * @create: 2020-12-02 16:16
 **/
@Data
public class BaseEntity {

    //@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Date created;
    private Date modified;

}
