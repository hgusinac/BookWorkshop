package se.haris.booklender.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.haris.booklender.model.dto.BookDTO;
import se.haris.booklender.model.entity.Book;
import se.haris.booklender.service.converion.DTOService;
import se.haris.booklender.service.intefaces.BookEntityService;
import se.haris.booklender.service.intefaces.BookService;
import se.haris.booklender.service.intefaces.LoanEntityService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookEntityService bookEntityService;
    private final LoanEntityService loanEntityService;
    private final DTOService dtoService;


    @Override
    public List<BookDTO> findByReserved(boolean reserved) {
        return bookEntityService.findByReserved(reserved).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findByAvailable(boolean available) {
        return bookEntityService.findByAvailable(available).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        return bookEntityService.findByTitle(title).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO create(BookDTO dto) {
        Book book = bookEntityService.create(dto);
        return dtoService.toFullDTO(book, new ArrayList<>());
    }

    @Override
    public BookDTO findById(String id) {
        Book book = bookEntityService.findById(id);
        return dtoService.toFullDTO(book,loanEntityService.findByBook(id));
    }

    @Override
    public List<BookDTO> findAll() {
        return bookEntityService.findAll().stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());

    }

    @Override
    public BookDTO update(String id, BookDTO dto) {
      Book book = bookEntityService.update(id,dto);
      return dtoService.toFullDTO(book,loanEntityService.findByBook(id));
    }

    @Override
    public boolean delete(String id) {
        return bookEntityService.delete(id);
    }
}
