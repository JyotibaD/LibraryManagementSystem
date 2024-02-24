package com.LMS.Controller;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;
import com.LMS.Service.AdminService;
import com.LMS.Service.PurchasedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    public AdminService adminService;
    @Autowired
    public PurchasedRecordService purchasedRecordService;

    @GetMapping("/{bookId}")
    public BookRecord getBookById(@PathVariable("bookId") long bookId){
        return adminService.getBookById(bookId);
    }

    @GetMapping("/bookName")
    public BookRecord getBookByName(@RequestParam("bookName") String bookName){
        return adminService.getBookByName(bookName);
    }

    @GetMapping("/getAllBooks")
    public List<BookRecord> getAllBooks(){
        return adminService.getAllBooks();
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody BookRecord bookRecord){
        return adminService.addBook(bookRecord);
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
}
