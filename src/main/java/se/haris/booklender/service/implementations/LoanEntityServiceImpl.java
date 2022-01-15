package se.haris.booklender.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.haris.booklender.database.LoanDAO;
import se.haris.booklender.model.dto.LoanDTO;
import se.haris.booklender.model.entity.Book;
import se.haris.booklender.model.entity.LibraryUser;
import se.haris.booklender.model.entity.Loan;
import se.haris.booklender.service.intefaces.BookEntityService;
import se.haris.booklender.service.intefaces.LibraryUserEntityService;
import se.haris.booklender.service.intefaces.LoanEntityService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanEntityServiceImpl implements LoanEntityService {


    private final LoanDAO loanDAO;
    private final BookEntityService bookEntityService ;
    private final LibraryUserEntityService libraryUserEntityService;


    @Override
    public Loan create(LoanDTO dto) {
        if (dto == null) throw new IllegalArgumentException("DTO was null");
        if (dto.getLoanTaker() == null || dto.getBook() == null){
            throw new IllegalArgumentException("DTO.LoanTaker or DTO.Book was null");
        }

        Book book = bookEntityService.findById(dto.getBook().getId());
        LibraryUser user = libraryUserEntityService.findById(dto.getLoanTaker().getId());


        if (book.isAvailable()){
            Loan loan = new Loan();
            loan.setLoanTaker(user);
            loan.setConcluded(false);
            loan.setBook(book);
            loan.setLoanDate(LocalDate.now());
            return loanDAO.save(loan);
        }else{
            throw new IllegalStateException("Book is not available");
        }
    }

    @Override
    public Loan findById(String id) {
       return loanDAO.findById(id)
               .orElseThrow(()-> new IllegalArgumentException("Lone whit id " + id + "could not be found"));

    }

    @Override
    public List<Loan> findAll() {
        return loanDAO.findAll();
    }

    @Override
    public Loan update(String id, LoanDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Dto was null");
        Loan loan = findById(id);

       loan.setLoanTaker(
               dto.getLoanTaker() == null ? null : libraryUserEntityService.findById(dto.getLoanTaker().getId())
       );
       loan.setBook(
               dto.getBook() == null ? null : bookEntityService.findById(dto.getBook().getId())
       );


       if (dto.getLoanDate() != null){
           loan.setLoanDate(dto.getLoanDate());
       }


       if (dto.isConcluded()){
           loan.setConcluded(dto.isConcluded());
       }

       return loanDAO.save(loan);
    }

    @Override
    public boolean delete(String id) {
       loanDAO.deleteById(id);
       return !loanDAO.existsById(id);
    }

    @Override
    public List<Loan> findByBook(String bookId) {
        return loanDAO.findByBookId(bookId);
    }

    @Override
    public List<Loan> findUserID(String id) {
       return loanDAO.findByUserId(id);
    }

    @Override
    public List<Loan> findByConcluded(boolean concluded) {
        return loanDAO.findByConcludedStatus(concluded);
    }
}
