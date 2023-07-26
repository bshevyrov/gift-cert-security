package com.epam.esm.veiw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends AbstractDTO {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createTime;
    private List<OrderItemDTO> orderItemDTOList;
    private CustomerDTO customerDTO;


}
