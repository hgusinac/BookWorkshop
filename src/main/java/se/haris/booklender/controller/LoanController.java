package se.haris.booklender.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.haris.booklender.model.dto.LoanDTO;
import se.haris.booklender.service.intefaces.LoanService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    @GetMapping("/api/v1/loans")
    public ResponseEntity<List<LoanDTO>> find(@RequestParam(name = "search", defaultValue = "all") String search,
                                              @RequestParam(name = "value", defaultValue = "") String value){
        List<LoanDTO> result;
        switch (search.toLowerCase()){
            case "all":
                result = loanService.findAll();
                break;
            case "user":
                result = loanService.findByUserId(String.valueOf(value));
                break;
            case "book":
                result = loanService.findByBookId(String.valueOf(value));
                break;
            case "concluded":
                result = loanService.findByConcluded(Boolean.parseBoolean(value));
                break;
            default:
                throw new IllegalArgumentException("Invalid search type: '" + search);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("/api/v1/loans")
    public ResponseEntity<LoanDTO>create(@RequestBody LoanDTO dto){
        return ResponseEntity.status(201).body(loanService.create(dto));
    }
    @GetMapping("/api/v1/loans/{id}")
    public ResponseEntity<LoanDTO>findById(@PathVariable String id){
        return ResponseEntity.ok(loanService.findById(id));
    }
    @PutMapping("/api/v1/loans/{id}")
    public ResponseEntity<LoanDTO>update(@PathVariable String id,@RequestBody LoanDTO dto){
        return ResponseEntity.ok(loanService.update(id,dto));
    }
    @DeleteMapping("/api/v1/loans/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable String id){
        return ResponseEntity.ok(loanService.delete(id));
    }
}
