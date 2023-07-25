package com.epam.esm.mapper;

import com.epam.esm.entity.CustomerEntity;
import com.epam.esm.veiw.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(CustomerEntity customerEntity);

    CustomerEntity toModel(CustomerDTO customerDTO);
    List<CustomerDTO> toDTOList(List<CustomerEntity> list);

}
