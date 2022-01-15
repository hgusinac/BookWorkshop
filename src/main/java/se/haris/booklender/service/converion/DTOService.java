package se.haris.booklender.service.converion;

import se.haris.booklender.model.dto.BookDTO;
import se.haris.booklender.model.dto.LibraryUserDTO;
import se.haris.booklender.model.dto.LoanDTO;
import se.haris.booklender.model.entity.Book;
import se.haris.booklender.model.entity.LibraryUser;
import se.haris.booklender.model.entity.Loan;

import java.util.List;

public interface DTOService {


    BookDTO toSmallDTO(Book book);

    BookDTO toFullDTO(Book book, List<Loan> LoanHistory);

    LibraryUserDTO toSmallDTO(LibraryUser libraryUser);

    LibraryUserDTO toFullDTO(LibraryUser libraryUser, List<Loan>activeLoans);

    LoanDTO toSmallDTO(Loan loan);

    LoanDTO toFullDTO(Loan loan);
}

