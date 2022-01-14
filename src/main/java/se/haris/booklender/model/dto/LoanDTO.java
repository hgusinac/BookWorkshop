package se.haris.booklender.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.haris.booklender.model.entity.Book;
import se.haris.booklender.model.entity.LibraryUser;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanDTO {


    private String id;
    private LibraryUserDTO loanTaker;
    private BookDTO book;
    private LocalDate loanDate;
    private boolean concluded;

}
