package com.epam.esm.mapper;

import com.epam.esm.entity.Customer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = Customer.class)
public interface CustomerListMapper {
    List<CustomerDTO> toDTOList(List<Customer> list);

}
