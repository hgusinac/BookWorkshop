package se.haris.booklender.service.intefaces;

import se.haris.booklender.model.dto.LibraryUserDTO;
import se.haris.booklender.model.entity.LibraryUser;

public interface LibraryUserEntityService extends GenericCRUD<LibraryUser, LibraryUserDTO,String> {

    LibraryUser findByEmail(String email);


}
