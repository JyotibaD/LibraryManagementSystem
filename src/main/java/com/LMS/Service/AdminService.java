package com.LMS.Service;

import com.LMS.Entity.BookRecord;

import java.util.List;


public interface AdminService {
    public BookRecord getBookById(long bookId);
    public BookRecord getBookByName(String bookName);
    public List<BookRecord> getAllBooks();
    public String addBook(BookRecord bookRecord);
    public  String updateBook(long bookId, BookRecord bookRecord);
    public  String deleteBookById(long bookId);
    String deleteAllBooks();
    public BookRecord findByBookAuthor(String authorName);

}
