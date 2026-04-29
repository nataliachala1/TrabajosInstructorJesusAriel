
package com.sena.library.service;

import com.sena.library.model.Book;
import com.sena.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Crear
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Listar todos
    public List<Book> listarbooks() {
        return bookRepository.findAll();
    }

    // Buscar por ID
    public Optional<Book> obtenerPorId(Long id) {
        return bookRepository.findById(id);
    }

    // update
    public Book actualizarBook(Long id, Book bookActualizado) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookActualizado.getTitle());
            book.setAuthor(bookActualizado.getAuthor());
            book.setAvailable(bookActualizado.getAvailable());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("book no encontrado"));
    }

    // delete
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}