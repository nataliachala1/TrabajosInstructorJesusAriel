package com.sena.library.service;

import com.sena.library.model.Users;
import com.sena.library.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    //  CREAR
    public Users crear(Users users) {
        return usersRepository.save(users);
    }

    //  LISTAR
    public List<Users> listar() {
        return usersRepository.findAll();
    }

    //  BUSCAR POR ID
    public Users buscar(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Users no encontrado"));
    }

    //  update
    public Users update(Long id, Users users) {
        Users existente = buscar(id);

        existente.setName(users.getName());
        existente.setEmail(users.getEmail());

        return usersRepository.save(existente);
    }

    //  delete
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}