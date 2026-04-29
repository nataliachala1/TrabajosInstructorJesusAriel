package com.sena.library.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDTO {

    private Long id;
    private Long userId;
    private Long bookId;
    private String nameUsers;
    private String titleBook;
    private LocalDate dateLoan;
    private LocalDate dateReturn;
}
