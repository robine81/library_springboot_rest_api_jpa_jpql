package com.springboot.library_rest_api_jpa_jpql.service.Mapper;

import com.springboot.library_rest_api_jpa_jpql.model.Loan;
import com.springboot.library_rest_api_jpa_jpql.model.dto.LoanResDTO;

public class LoanMapper {
    public static LoanResDTO toResponseDTO(Loan loan){ return new LoanResDTO(loan.getLoanId(), loan.getStartDate(), loan.getReturnDate(), loan.getUser().getUserId(), loan.getUser().getUserName(), loan.getBook().getBookId(), loan.getBook().getTitle()); }
}
