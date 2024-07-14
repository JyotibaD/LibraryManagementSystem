package com.LMS.Controller;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;
import com.LMS.Exception.BookAllreadyPresentException;
import com.LMS.Exception.ErrorDetails;
import com.LMS.Exception.ResourceNotFoundException;
import com.LMS.Service.AdminService;
import com.LMS.Service.PurchasedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    public AdminService adminService;
    @Autowired
    public PurchasedRecordService purchasedRecordService;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookRecord> getBookById(@PathVariable("bookId") long bookId){
        BookRecord bookRecord= adminService.getBookById(bookId);
        return ResponseEntity.ok(bookRecord);
    }

    @GetMapping("/bookName")
    public ResponseEntity<Optional<BookRecord>> getBookByName(@RequestParam("bookName") String bookName){

        Optional<BookRecord> bookRecord= adminService.getBookByName(bookName);

        return ResponseEntity.ok(bookRecord);

    }

    @GetMapping("/getAllBooks")
    public List<BookRecord> getAllBooks(){
        return adminService.getAllBooks();
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody BookRecord bookRecord){
        String message=adminService.addBook(bookRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/{bookId}")
    public String updateBook(@PathVariable("bookId") long bookId,@RequestBody BookRecord bookRecord){
        return adminService.updateBook(bookId, bookRecord);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable("bookId") long bookId){
        return  adminService.deleteBookById(bookId);
    }
    @DeleteMapping("/deleteAllBooks")
    public String deleteBook(){
        return  adminService.deleteAllBooks();
    }

    @GetMapping("/getAllBorrowedRecord")
    public List<PurchasedRecord> getAllBorrowedRecord(){
        return purchasedRecordService.getAllBorrowedRecord();
    }

    @GetMapping("/getBookByBookAuthorName")
    public ResponseEntity<Optional<BookRecord>> findByBookAuthor(@RequestParam("bookAuthor") String bookAuthor){

        Optional<BookRecord> bookRecord= adminService.findByBookAuthor(bookAuthor);

        return ResponseEntity.ok(bookRecord);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Resource not found");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAllreadyPresentException.class)
    public ResponseEntity<ErrorDetails> BookAllreadyPresentException(BookAllreadyPresentException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "User already exists");
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
}
