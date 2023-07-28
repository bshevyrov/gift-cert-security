package com.epam.esm.veiw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends AbstractDTO {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createTime;
    private List<OrderItemDTO> orderItemDTOS;
    private CustomerDTO customerDTO;


}
