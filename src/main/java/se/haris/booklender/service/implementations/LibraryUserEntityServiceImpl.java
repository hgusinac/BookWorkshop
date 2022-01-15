package se.haris.booklender.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.haris.booklender.database.LibraryUserDAO;
import se.haris.booklender.model.dto.LibraryUserDTO;
import se.haris.booklender.model.entity.LibraryUser;
import se.haris.booklender.service.intefaces.LibraryUserEntityService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryUserEntityServiceImpl implements LibraryUserEntityService {

    private final LibraryUserDAO libraryUserDAO;

    @Override
    public LibraryUser create(LibraryUserDTO dto) {

        if (dto == null) throw new IllegalArgumentException("DTO was null");
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setName(dto.getName());
        libraryUser.setEmail(dto.getEmail());
        return libraryUserDAO.save(libraryUser);

    }

    @Override
    public LibraryUser findById(String id) {
        return libraryUserDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find User whit id " + id));
    }

    @Override
    public List<LibraryUser> findAll() {
        return libraryUserDAO.findAll();
    }

    @Override
    public LibraryUser update(String id, LibraryUserDTO dto) {
        if (dto == null) throw new IllegalArgumentException("DTO was null");
        LibraryUser libraryUser = findById(id);
        Optional<LibraryUser> optional = libraryUserDAO.findByEmail(dto.getEmail());
        if (optional.isPresent() && !id.equals(optional.get().getId())){
            throw new IllegalArgumentException("Email is already in use");
        }
        libraryUser.setName(dto.getName());
        libraryUser.setEmail(dto.getEmail());
        return libraryUserDAO.save(libraryUser);
    }

    @Override
    public boolean delete(String id) {
        libraryUserDAO.deleteById(id);
        return libraryUserDAO.existsById(id);
    }

    @Override
    public LibraryUser findByEmail(String email) {
        return libraryUserDAO.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("Could not find Email "));
    }
}
