package com.sena.library.service;

import com.sena.library.dto.LoanDTO;
import com.sena.library.mapper.LoanMapper;
import com.sena.library.model.Book;
import com.sena.library.model.Loan;
import com.sena.library.model.Users;
import com.sena.library.repository.BookRepository;
import com.sena.library.repository.LoanRepository;
import com.sena.library.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository prestamoRepository;
    private final UsersRepository usersRepository;
    private final BookRepository bookRepository;

    // 📌 CREAR PRÉSTAMO
    public LoanDTO crearLoan(LoanDTO dto) {

        Users users = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Users no encontrado"));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book no encontrado"));

        // 🔥 VALIDAR DISPONIBILIDAD
        if (!book.getAvailable()) {
            throw new RuntimeException("El book no está disponible");
        }

        Loan loan = Loan.builder()
                .users(users)
                .book(book)
                .dateLoan(LocalDate.now())
                .dateReturn(dto.getDateReturn())
                .build();

        // 🔒 Marcar book como prestado
        book.setAvailable(false);
        bookRepository.save(book);

        return LoanMapper.toDTO(prestamoRepository.save(loan));
    }

    // 📌 LISTAR
    public List<LoanDTO> listarLoans() {
        return prestamoRepository.findAll()
                .stream()
                .map(LoanMapper::toDTO)
                .toList();
    }

    // 📌 BUSCAR
    public LoanDTO buscarLoan(Long id) {
        Loan loan = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        return LoanMapper.toDTO(loan);
    }

    // 📌 update
    public LoanDTO actualizarLoan(Long id, LoanDTO dto) {

        Loan loan = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        loan.setDateReturn(dto.getDateReturn());

        return LoanMapper.toDTO(prestamoRepository.save(loan));
    }

    // 📌 delete (DEVOLUCIÓN)
    public void eliminarLoan(Long id) {

        Loan loan = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        // 🔓 Liberar book
        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        prestamoRepository.deleteById(id);
    }
}