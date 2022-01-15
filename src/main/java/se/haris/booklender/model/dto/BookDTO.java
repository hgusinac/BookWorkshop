package se.haris.booklender.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {


    private String id;
    private String title;
    private boolean available;
    private boolean reserved;
    private Integer maxLoanDays;
    private BigDecimal finePerDay;
    private String description;
    private List<LoanDTO> loanHistory;
}
