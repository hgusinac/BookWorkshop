package se.haris.booklender.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.haris.booklender.database.BookDAO;
import se.haris.booklender.model.dto.BookDTO;
import se.haris.booklender.model.entity.Book;
import se.haris.booklender.service.intefaces.BookEntityService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookEntityServiceImpl implements BookEntityService {

    private final BookDAO bookDAO;



    @Override
    public List<Book> findByReserved(boolean reserved) {
        return bookDAO.findByReservedStatus(reserved);
    }

    @Override
    public List<Book> findByAvailable(boolean available) {
        return bookDAO.findByAvailableStatus(available);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookDAO.findByTitle(title);
    }

    @Override
    public Book create(BookDTO dto) {
        if (dto == null) throw new IllegalArgumentException("DTO was null");
        Book book = new Book();
        book = internalCopy(book,dto);

        return bookDAO.save(book);
    }

    public Book internalCopy(Book book,BookDTO dto){
        book.setAvailable(dto.isAvailable());
        book.setDescription(dto.getDescription());
        book.setFinePerDay(dto.getFinePerDay());
        book.setReserved(dto.isReserved());
        book.setMaxLoanDays(dto.getMaxLoanDays());
        book.setTitle(dto.getTitle());
        return book;

    }

    @Override
    public Book findById(String id) {
        return  bookDAO.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Id was not found" + id ));

    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book update(String id, BookDTO dto) {
        if (dto == null) throw new IllegalArgumentException("DTO was null");
        Book book = findById(id);
        book = internalCopy(book,dto);
        return bookDAO.save(book);
    }

    @Override
    public boolean delete(String id) {
        bookDAO.deleteById(id);
        return !bookDAO.existsById(id);
    }
}
