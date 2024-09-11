package com.uberApplication.uber.DTO;

import java.util.Set;

import com.uberApplication.uber.entities.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name,email;

    private Set<Role> roles;
}
