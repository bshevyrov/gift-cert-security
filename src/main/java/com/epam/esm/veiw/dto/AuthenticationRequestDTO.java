package com.epam.esm.veiw.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationRequestDTO {
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[\\w]+$", message = "Username contains illegal chars.")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^[\\w]+$", message = "Password contains illegal chars.")
    private String password;
}
