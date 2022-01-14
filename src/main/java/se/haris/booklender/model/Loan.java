package se.haris.booklender.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
public class Loan {

    @Id
    @GeneratedValue(generator = GENERATOR)
    @GenericGenerator(name = GENERATOR, strategy = UUID_GENERATOR)
    @Column(updatable = false)

    private String id;

    @ManyToOne(

            cascade = {CascadeType.DETACH,CascadeType.REFRESH},
            fetch = FetchType.LAZY


    )
    private LibraryUser loanTaker;

    @ManyToOne(

            cascade = {CascadeType.DETACH,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private Book book;
    private LocalDate loanDate;
    private boolean terminated;

}
