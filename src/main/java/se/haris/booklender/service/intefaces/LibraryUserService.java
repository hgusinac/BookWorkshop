package se.haris.booklender.service.intefaces;

import se.haris.booklender.database.LibraryUserDAO;
import se.haris.booklender.model.dto.LibraryUserDTO;

public interface LibraryUserService extends GenericCRUD<LibraryUserDTO,LibraryUserDTO,String>{
    LibraryUserDTO findByEmail(String email);
}
