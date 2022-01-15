package se.haris.booklender.service.intefaces;

import se.haris.booklender.model.dto.BookDTO;

import java.util.List;

public interface BookService extends GenericCRUD<BookDTO,BookDTO,String>{
    List<BookDTO>findByReserved(boolean reserved);
    List<BookDTO>findByAvailable(boolean available);
    List<BookDTO>findByTitle(String title);

}
