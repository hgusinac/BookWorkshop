package se.haris.booklender.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

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
    @JoinColumn(name = "fk_loanTaker_id")
    private LibraryUser loanTaker;

    @ManyToOne(

            cascade = {CascadeType.DETACH,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "fk_books_id")
    private Book book;
    private LocalDate loanDate;
    private boolean concluded;

    public boolean isOverdue(){
        LocalDate dueDate = loanDate.plusDays(book.getMaxLoanDays());
        boolean isOverdue = LocalDate.now().isAfter(dueDate);
        return isOverdue;
    }

    public BigDecimal getFine(){
        Period p = Period.between(loanDate.plusDays(book.getMaxLoanDays()),LocalDate.now());
        int d = p.getDays();
        BigDecimal fine = BigDecimal.ZERO;
        if (d>0){
            fine = BigDecimal.valueOf(d*book.getFinePerDay().longValue());
        }
        return fine;
    }

    public boolean extendLoan(){
        if (book.isReserved() || isOverdue()){
            return false;
        }
        this.loanDate = LocalDate.now();
        return true;
    }

}
