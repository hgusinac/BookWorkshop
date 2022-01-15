package se.haris.booklender.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.haris.booklender.model.entity.Loan;

import java.util.List;

public interface LoanDAO extends JpaRepository<Loan,String> {

    @Query("SELECT l FROM Loan l WHERE l.loanTaker.id = :userId")
    List<Loan> findByUserId(@Param("userId") String userId);

    @Query("SELECT l FROM Loan l where l.book = :bookId")
    List<Loan> findByBookId(@Param("bookId") String bookId);

    @Query("SELECT l FROM Loan l WHERE l.concluded = :concluded")
    List<Loan> findByConcludedStatus(@Param("concluded") boolean concluded);
}
