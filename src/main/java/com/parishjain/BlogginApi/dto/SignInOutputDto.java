package com.parishjain.BlogginApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInOutputDto {

    private String token;
    private String message;

}
