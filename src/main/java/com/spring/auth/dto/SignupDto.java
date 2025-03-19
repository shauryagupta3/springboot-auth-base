package com.spring.auth.dto;

import com.spring.auth.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupDto {
    private String username;
    private String password;
    UserRole role;
}
