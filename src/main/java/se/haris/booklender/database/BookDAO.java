package se.haris.booklender.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.haris.booklender.model.entity.Book;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,String> {

   @Query("SELECT b from Book b WHERE  b.reserved = :reserved")
   List<Book> findByReservedStatus(@Param("reserved") boolean reserved);

   @Query("SELECT b from Book b WHERE b.available = :available")
      List<Book> findByAvailableStatus(@Param("available")boolean available);

   @Query("SELECT b from Book b WHERE UPPER(b.title) = UPPER(:title)")

   List<Book> findByTitle (@Param("title") String title);

}
