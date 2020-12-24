package com.critina.eblog.common.convert;

import com.critina.eblog.search.model.PostDocument;
import com.critina.eblog.vo.PostVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDocument postVoToPostDocument(PostVo postVo);

}
