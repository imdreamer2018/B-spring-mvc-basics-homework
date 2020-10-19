package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    @NotBlank
    @Pattern(regexp = "\\w{3,10}")
    private String username;

    @NotBlank
    @Size(min = 5, max = 12)
    private String password;

    @Email
    private String email;
}
