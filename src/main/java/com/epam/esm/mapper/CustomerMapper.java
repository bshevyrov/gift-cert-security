package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.veiw.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface CustomerMapper {
    //    @Mapping(target = "orderDTOS", source = "orderEntities")
    CustomerDTO toDTO(CustomerEntity customerEntity);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    CustomerEntity toEntity(CustomerDTO customerDTO);

    List<CustomerDTO> toDTOList(List<CustomerEntity> list);

}
