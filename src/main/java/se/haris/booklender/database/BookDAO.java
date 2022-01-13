package se.haris.booklender.database;

import org.springframework.data.jpa.repository.JpaRepository;
import se.haris.booklender.model.Book;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,String> {
  List<Book> findAllByReserved(boolean reserved);
  List<Book> findAllByAvailable(boolean available);
  Book findByTitle(String title);
}
