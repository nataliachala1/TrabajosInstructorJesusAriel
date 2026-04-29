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

import com.sena.library.dto.LoanDTO;
import com.sena.library.service.LoanService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public LoanDTO crear(@RequestBody LoanDTO dto) {
        return loanService.crearLoan(dto);
    }

    @GetMapping
    public List<LoanDTO> listar() {
        return loanService.listarLoans();
    }

    @GetMapping("/{id}")
    public LoanDTO buscar(@PathVariable Long id) {
        return loanService.buscarLoan(id);
    }

    @PutMapping("/{id}")
    public LoanDTO update(@PathVariable Long id, @RequestBody LoanDTO dto) {
        return loanService.actualizarLoan(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        loanService.eliminarLoan(id);
    }
}