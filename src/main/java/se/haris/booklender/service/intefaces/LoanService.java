package se.haris.booklender.service.intefaces;

import se.haris.booklender.model.dto.BookDTO;
import se.haris.booklender.model.dto.LoanDTO;

import java.util.List;

public interface LoanService extends GenericCRUD<LoanDTO,LoanDTO,String>{
    List<LoanDTO> findByBookId(String id);
    List<LoanDTO> findByUserId(String id);
    List<LoanDTO> findByConcluded(boolean concluded);



}
