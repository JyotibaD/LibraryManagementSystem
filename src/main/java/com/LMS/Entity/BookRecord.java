package com.LMS.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="booksRecord")
public class BookRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotBlank(message = "Book Name is mandatory")
    @Size(min = 3,max = 50,message = "Book Name must be between 3 and 50 characters")
    private String bookName;

    @NotNull(message = "Book copies are mandatory")
    @Min(value = 0, message = "Copies must be at least 0")
    @Max(value = 100, message = "Copies must be at most 100")
    private int copies;

    @NotBlank(message = "Book Author is mandatory")
    @Size(min = 3,max = 50,message = "Book Author name must be between 3 and 50 characters")
    private String bookAuthor;



    public BookRecord(String bookName, int copies, String bookAuthor) {
        this.bookName = bookName;
        this.copies = copies;
        this.bookAuthor = bookAuthor;
    }

}
