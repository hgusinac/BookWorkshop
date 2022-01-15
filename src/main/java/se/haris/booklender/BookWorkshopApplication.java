package se.haris.booklender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.haris.booklender.model.entity.Loan;

@SpringBootApplication
public class BookWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookWorkshopApplication.class, args);
        Loan loan = new Loan();
    }


}
