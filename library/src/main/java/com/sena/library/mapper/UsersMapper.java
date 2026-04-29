package com.sena.library.mapper;

import com.sena.library.dto.UsersDTO;
import com.sena.library.model.Users;

public class UsersMapper {

    // Entity → DTO
    public static UsersDTO toDTO(Users users) {
        if (users == null) return null;

        return UsersDTO.builder()
                .id(users.getId())
                .name(users.getName())
                .email(users.getEmail())
                .build();
    }

    // DTO → Entity
    public static Users toEntity(UsersDTO dto) {
        if (dto == null) return null;

        return Users.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}