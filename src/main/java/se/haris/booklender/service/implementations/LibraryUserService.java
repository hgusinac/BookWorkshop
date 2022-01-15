package se.haris.booklender.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.haris.booklender.model.dto.LibraryUserDTO;
import se.haris.booklender.model.entity.LibraryUser;
import se.haris.booklender.model.entity.Loan;
import se.haris.booklender.service.converion.DTOService;
import se.haris.booklender.service.intefaces.BookEntityService;
import se.haris.booklender.service.intefaces.LibraryUserEntityService;
import se.haris.booklender.service.intefaces.LoanEntityService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryUserService implements se.haris.booklender.service.intefaces.LibraryUserService {

    private final LibraryUserEntityService libraryUserEntityService ;
    private final LoanEntityService loanEntityService;
    private final DTOService dtoService;

    @Override
    public LibraryUserDTO create(LibraryUserDTO dto) {
        return dtoService.toFullDTO(
                libraryUserEntityService.create(dto),
                new ArrayList<>()
        );
    }

    @Override
    public LibraryUserDTO findById(String id) {
        return dtoService.toFullDTO(
                libraryUserEntityService.findById(id),
                loanEntityService.findUserID(id)
        );
    }

    @Override
    public List<LibraryUserDTO> findAll() {
        return libraryUserEntityService.findAll().stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryUserDTO update(String id, LibraryUserDTO dto) {
        return dtoService.toFullDTO(
                libraryUserEntityService.update(id,dto),
                loanEntityService.findUserID(id)
        );
    }

    @Override
    public boolean delete(String id) {
        return libraryUserEntityService.delete(id);
    }

    @Override
    public LibraryUserDTO findByEmail(String email) {
        LibraryUser user = libraryUserEntityService.findByEmail(email);
        List<Loan> loans = loanEntityService.findUserID(user.getId());
        return dtoService.toFullDTO(
                user,loans
        );
    }
}
