package se.haris.booklender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.haris.booklender.model.dto.LibraryUserDTO;
import se.haris.booklender.service.implementations.LibraryUserEntityServiceImpl;
import se.haris.booklender.service.intefaces.LibraryUserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryUserController {

    private final LibraryUserService libraryUserService;


    @GetMapping("/api/v1/users")
    public ResponseEntity<?> find(@RequestParam(value = "email") String email){
        if(email == null){
            return ResponseEntity.ok(libraryUserService.findAll());
        }
        return ResponseEntity.ok(libraryUserService.findByEmail(email));
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<LibraryUserDTO>create (@RequestBody LibraryUserDTO dto){
        return ResponseEntity.status(201).body(libraryUserService.create(dto));
    }
    @GetMapping("/api/v1/users/{id}")
    private ResponseEntity<LibraryUserDTO>findById(@PathVariable("id") String id){
        return ResponseEntity.ok(libraryUserService.findById(id));

    }
    @PostMapping("/aoi/v1/users/{id}")
    public ResponseEntity<LibraryUserDTO>update(String id,LibraryUserDTO dto){
        return ResponseEntity.ok(libraryUserService.update(id,dto));
    }
    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable("id") String id){
        return ResponseEntity.ok().body(libraryUserService.delete(id));
    }
}
