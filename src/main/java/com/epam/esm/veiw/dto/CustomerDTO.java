package com.epam.esm.veiw.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends BaseDTO {
    private List<OrderDTO> orderDTOS;
}
