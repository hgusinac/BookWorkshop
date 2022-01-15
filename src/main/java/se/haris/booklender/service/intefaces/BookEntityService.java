package se.haris.booklender.service.intefaces;

import se.haris.booklender.model.dto.BookDTO;
import se.haris.booklender.model.entity.Book;

import java.util.List;

public interface BookEntityService extends GenericCRUD<Book, BookDTO,String> {
    List<Book> findByReserved(boolean reserved);
    List<Book> findByAvailable(boolean available);
    List<Book> findByTitle(String title);
}
