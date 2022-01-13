package se.haris.booklender.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.haris.booklender.model.LibraryUser;

public interface LibraryUserDAO extends JpaRepository<LibraryUserDAO,String> {
    LibraryUser findByEmailIgnoringCase(String email);
}
