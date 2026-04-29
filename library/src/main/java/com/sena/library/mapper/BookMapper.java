package com.sena.library.mapper;

import com.sena.library.dto.BookDTO;
import com.sena.library.model.Book;

public class BookMapper {

    // Entity → DTO
    public static BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .available(book.getAvailable())
                .build();
    }

    // DTO → Entity
    public static Book toEntity(BookDTO dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .available(dto.getAvailable())
                .build();
    }
}
