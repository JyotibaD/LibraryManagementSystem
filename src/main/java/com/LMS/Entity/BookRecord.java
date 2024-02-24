package com.LMS.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="booksRecord")
public class BookRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private int copies;
    private String bookAuthor;

    public BookRecord() {

    }

    public BookRecord(String bookName, int copies, String bookAuthor) {
        this.bookName = bookName;
        this.copies = copies;
        this.bookAuthor = bookAuthor;
    }

}
