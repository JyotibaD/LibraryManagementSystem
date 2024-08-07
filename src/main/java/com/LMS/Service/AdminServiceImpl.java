package com.LMS.Service;

import com.LMS.Entity.BookRecord;
import com.LMS.Exception.BookAllreadyPresentException;
import com.LMS.Exception.ResourceNotFoundException;
import com.LMS.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    public AdminRepository adminRepository;

    @Override
    public BookRecord getBookById(long bookId) {

        return adminRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
    }

    @Override
    public Optional<BookRecord> getBookByName(String bookName) {
        Optional<BookRecord> b = Optional.ofNullable(adminRepository.findByBookName(bookName)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with Name: " + bookName)));

        return b;
    }

    @Override
    public List<BookRecord> getAllBooks() {
        return adminRepository.findAll();
    }

    @Override
    public String addBook(BookRecord bookRecord) {
        String bName=bookRecord.getBookName();
        Optional b=adminRepository.findByBookName(bName);

        if(b.isPresent()){
            throw new BookAllreadyPresentException("Book allready present with name: "+bName);
            //return "Book allready present with name"+bName;
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
            throw new ResourceNotFoundException("Book not found with ID: "+bookId);
        }
        bookRecord1.setBookName(bookRecord.getBookName());
        bookRecord1.setBookAuthor(bookRecord.getBookAuthor());
        bookRecord1.setCopies(bookRecord.getCopies());
        adminRepository.save(bookRecord1);
        return "Successfully updated "+bookId;
    }

    @Override
    public String deleteBookById(long bookId) {
        Optional<BookRecord> b = Optional.ofNullable(adminRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId)));

        if(b.isPresent())
            adminRepository.deleteById(bookId);

        return "Successfully deleted..";
    }

    @Override
    public String deleteAllBooks() {
        adminRepository.deleteAll();
        return "all books deleted successfully";
    }

    @Override
    public List<BookRecord> findByBookAuthor(String authorName) {
        List<BookRecord> bookRecord= adminRepository.findByBookAuthor(authorName);
        if (bookRecord.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with Author Name: " + authorName);
        }
        return bookRecord;
    }
}
