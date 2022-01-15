package se.haris.booklender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.haris.booklender.model.dto.BookDTO;
import se.haris.booklender.service.intefaces.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

@GetMapping("/api/v1/books")
    public ResponseEntity<List<BookDTO>>find(
            @RequestParam(value = "search", defaultValue = "all") String search,
            @RequestParam(value = "value", defaultValue = "") String value){

        switch (search.toLowerCase()){
            case "all":
                return ResponseEntity.ok(bookService.findAll());
            case "title":
                return ResponseEntity.ok(bookService.findByTitle(value));
            case "available":
                return ResponseEntity.ok(bookService.findByAvailable(Boolean.parseBoolean(value)));
            case "reserved":
                return ResponseEntity.ok(bookService.findByReserved(Boolean.parseBoolean(value)));
            default:
                throw new IllegalStateException("Invalid search type ");
        }

    }

    @PostMapping("/api/v1/books")
    public ResponseEntity<BookDTO> create(@RequestParam BookDTO dto){
    return ResponseEntity.status(201).body(bookService.create(dto));
    }


    @GetMapping("/api/v1/books/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") String id){
    return ResponseEntity.ok(bookService.findById(id));
    }

    @PutMapping("/api/v1/books/{id}")
    public ResponseEntity<BookDTO>update(@PathVariable("id") String id,@RequestBody BookDTO bookDTO){
    return ResponseEntity.ok(bookService.update(id,bookDTO));
    }
    @DeleteMapping("/api/v1/book/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable("id") String id){
    return ResponseEntity.ok(bookService.delete(id));
    }
}
