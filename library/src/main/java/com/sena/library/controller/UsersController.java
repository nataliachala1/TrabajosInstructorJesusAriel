package com.sena.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.library.dto.UsersDTO;
import com.sena.library.mapper.UsersMapper;
import com.sena.library.service.UsersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public UsersDTO crear(@RequestBody UsersDTO dto) {
        return UsersMapper.toDTO(
                usersService.crear(UsersMapper.toEntity(dto))
        );
    }

    @GetMapping
    public List<UsersDTO> listar() {
        return usersService.listar()
                .stream()
                .map(UsersMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UsersDTO buscar(@PathVariable Long id) {
        return UsersMapper.toDTO(usersService.buscar(id));
    }

    @PutMapping("/{id}")
    public UsersDTO update(@PathVariable Long id, @RequestBody UsersDTO dto) {
        return UsersMapper.toDTO(
                usersService.update(id, UsersMapper.toEntity(dto))
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usersService.delete(id);
    }
}