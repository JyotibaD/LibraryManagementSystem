package com.LMS.Controller;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;
import com.LMS.Service.AdminService;
import com.LMS.Service.PurchasedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchasedRecord")
public class PurchasedRecordController {
    @Autowired
    public AdminService adminService;
    @Autowired
    public PurchasedRecordService purchasedRecordService;


    @GetMapping("/getAllBooks")
    public List<BookRecord> getAllBooks(){
        return adminService.getAllBooks();
    }
    @GetMapping("/getAllBooksByName/bookName")
    public List<BookRecord> getAllBooksByName(@RequestParam("bookName") String bookName ){
        return purchasedRecordService.getAllBooksByName(bookName);

    }

    @GetMapping("/bookName")
    public BookRecord getBookByName(@RequestParam("bookName") String bookName){

        return adminService.getBookByName(bookName);

    }

    @PostMapping("/borrowBook")
    public String borrowBook(@RequestBody PurchasedRecord purchasedRecord){
        return purchasedRecordService.barrowBook(purchasedRecord);
    }

}
