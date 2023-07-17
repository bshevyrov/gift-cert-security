package com.epam.esm.mapper;

import com.epam.esm.entity.Customer;
import com.epam.esm.entity.Tag;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.dto.TagDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);

    Customer toModel(CustomerDTO customerDTO);
}
