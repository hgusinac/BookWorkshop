package se.haris.booklender.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LibraryUserDTO {

    private String id;
    private LocalDate regDate;
    private String name;
    private String email;
}
