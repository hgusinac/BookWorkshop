package se.haris.booklender.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.loader.entity.UniqueEntityLoader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static se.haris.booklender.model.constants.EntityConstants.GENERATOR;
import static se.haris.booklender.model.constants.EntityConstants.UUID_GENERATOR;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class LibraryUser {

    @Id
    @GeneratedValue(generator = GENERATOR)
    @GenericGenerator(name = GENERATOR, strategy = UUID_GENERATOR)
    @Column(updatable = false)
    private String id;
    private LocalDate regDate;
    private String name;
    @Column(unique = true)
    private String email;
}
