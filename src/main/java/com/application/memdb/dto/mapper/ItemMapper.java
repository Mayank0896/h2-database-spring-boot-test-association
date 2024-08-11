package com.application.memdb.dto.mapper;

import com.application.memdb.dto.ItemDTO;
import com.application.memdb.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDTO itemToItemDTO(Item item);

    Item itemDTOToItem(ItemDTO itemDTO);
}
