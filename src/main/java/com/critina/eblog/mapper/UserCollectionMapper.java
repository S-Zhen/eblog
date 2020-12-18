package com.critina.eblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.critina.eblog.entity.UserCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.critina.eblog.vo.UserCollectionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author critina
 * @since 2020-12-02
 */
@Component
public interface UserCollectionMapper extends BaseMapper<UserCollection> {

    IPage<UserCollectionVo> selectCollection(Page page,@Param(Constants.WRAPPER) QueryWrapper<UserCollection> wrapper);
}
