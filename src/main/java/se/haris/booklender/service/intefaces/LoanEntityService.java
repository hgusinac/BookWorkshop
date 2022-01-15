package se.haris.booklender.service.intefaces;

import se.haris.booklender.model.dto.LoanDTO;
import se.haris.booklender.model.entity.Loan;

import java.util.List;

public interface LoanEntityService extends GenericCRUD<Loan, LoanDTO,String> {

    List<Loan> findByBook(String bookId);
    List<Loan> findUserID(String id);
    List<Loan> findByConcluded  (boolean concluded);

}
