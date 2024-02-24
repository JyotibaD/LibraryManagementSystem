package com.LMS.Service;

import com.LMS.Entity.BookRecord;
import com.LMS.Repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    public AdminRepository adminRepository;


    @Transactional
    @Override
    public BookRecord getBookById(long bookId) {
        return adminRepository.findByBookId(bookId);
    }

    @Override
    public BookRecord getBookByName(String bookName) {
        return adminRepository.findByBookName(bookName);
    }

    @Override
    public List<BookRecord> getAllBooks() {
        return adminRepository.findAll();
    }

    @Override
    public String addBook(BookRecord bookRecord) {
        String bName=bookRecord.getBookName();
        BookRecord b=adminRepository.findByBookName(bName);

        if(b != null){
            return "Book allready present with name"+bName;
        }
        else {
            adminRepository.save(bookRecord);
            return "Book added successfully";
        }
    }

    @Override
    public String updateBook(long bookId, BookRecord bookRecord) {
        BookRecord bookRecord1 =adminRepository.findByBookId(bookId);
        if (bookRecord1 ==null){
            return "book not found";
        }
        bookRecord1.setBookName(bookRecord.getBookName());
        bookRecord1.setBookAuthor(bookRecord.getBookAuthor());
        bookRecord1.setCopies(bookRecord.getCopies());
        adminRepository.save(bookRecord1);
        return "Successfully updated "+bookId;
    }

    @Override
    public String deleteBookById(long bookId) {
        adminRepository.deleteById(bookId);
        return "Successfully deleted..";
    }

    @Override
    public String deleteAllBooks() {
        adminRepository.deleteAll();
        return "all books deleted successfully";
    }
}
