package com.sena.library.mapper;

import com.sena.library.dto.LoanDTO;
import com.sena.library.model.Loan;

public class LoanMapper {

    // Entity → DTO
    public static LoanDTO toDTO(Loan loan) {
        if (loan == null) return null;

        return LoanDTO.builder()
                .id(loan.getId())
                .userId(loan.getUsers().getId())
                .nameUsers(loan.getUsers().getName())
                .bookId(loan.getBook().getId())
                .titleBook(loan.getBook().getTitle())
                .dateLoan(loan.getDateLoan())
                .dateReturn(loan.getDateReturn())
                .build();
    }
}