package com.LMS.Controller;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;
import com.LMS.Exception.BookAllreadyPresentException;
import com.LMS.Exception.ErrorDetails;
import com.LMS.Exception.ResourceNotFoundException;
import com.LMS.Service.AdminService;
import com.LMS.Service.PurchasedRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PurchasedRecordService purchasedRecordService;

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
    public ResponseEntity<String> addBook(@Valid @RequestBody BookRecord bookRecord){
        String message=adminService.addBook(bookRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable("bookId") long bookId,@Valid @RequestBody BookRecord bookRecord){
        String message= adminService.updateBook(bookId, bookRecord);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") long bookId){
        String message= adminService.deleteBookById(bookId);
        return  ResponseEntity.ok(message);
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
    public ResponseEntity<List<BookRecord>> findByBookAuthor(@RequestParam("bookAuthor") String bookAuthor) {

        List<BookRecord> bookRecord = adminService.findByBookAuthor(bookAuthor);

        return ResponseEntity.ok(bookRecord);

    }
}
