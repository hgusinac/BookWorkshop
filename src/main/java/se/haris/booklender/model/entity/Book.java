package se.haris.booklender.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

import static se.haris.booklender.model.constants.EntityConstants.GENERATOR;
import static se.haris.booklender.model.constants.EntityConstants.UUID_GENERATOR;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Book {

    @Id
    @GeneratedValue(generator = GENERATOR)
    @GenericGenerator(name = GENERATOR, strategy = UUID_GENERATOR)
    @Column(updatable = false)

    private String id;
    private String title;

    private boolean available;
    private boolean reserved;
    private Integer maxLoanDays;
    private BigDecimal finePerDay;
    private String description;
}
