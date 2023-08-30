package com.epam.esm.veiw.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CustomerDTO extends AbstractDTO {
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[\\w]+$", message = "Username contains illegal chars.")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^[\\w]+$", message = "Password contains illegal chars.")
    @JsonIgnore
    private String password;
}