package se.haris.booklender.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.haris.booklender.model.entity.LibraryUser;

import java.util.Optional;

public interface LibraryUserDAO extends JpaRepository<LibraryUser,String> {

   @Query("SELECT u from LibraryUser u where UPPER(u.email) = UPPER(:email)")
   Optional<LibraryUser> findByEmail (@Param("email")String email);

}
