package se.haris.booklender.database;

import org.springframework.data.jpa.repository.JpaRepository;
import se.haris.booklender.model.Loan;

import java.util.List;

public interface LoanDAO extends JpaRepository<Loan,String> {
    List<Loan> findAllByLoanTaker_Id (String userId);
    List<Loan> findAllByBook_Id (String bookId);
    List<Loan> findAllByTerminated (boolean terminated);
}
