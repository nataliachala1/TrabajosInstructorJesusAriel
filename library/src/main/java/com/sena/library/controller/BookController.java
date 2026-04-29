package com.sena.library.controller;

import com.sena.library.dto.BookDTO;
import com.sena.library.mapper.BookMapper;
import com.sena.library.model.Book;
import com.sena.library.service.BookService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin("*")

public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDTO crear(@RequestBody BookDTO dto) {
        Book book = BookMapper.toEntity(dto);
        Book guardado = bookService.createBook(book);
        return BookMapper.toDTO(guardado);
    }


}
