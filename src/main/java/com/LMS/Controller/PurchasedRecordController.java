package com.LMS.Controller;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;
import com.LMS.Exception.ResourceNotFoundException;
import com.LMS.Service.AdminService;
import com.LMS.Service.PurchasedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<BookRecord> getBookByName(@RequestParam("bookName") String bookName){
        Optional<BookRecord> bookRecord= adminService.getBookByName(bookName);

        return bookRecord;

    }

    @PostMapping("/borrowBook")
    public ResponseEntity<String> borrowBook(@RequestBody PurchasedRecord purchasedRecord) {
        String message= purchasedRecordService.barrowBook(purchasedRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/getPurchasedUserByUserName")
    public ResponseEntity<Optional<PurchasedRecord>> getPurchasedUserByUserName(@RequestParam("userName") String userName){

        Optional<PurchasedRecord> pUser= Optional.ofNullable(purchasedRecordService.findByUserName(userName));

        return ResponseEntity.ok(pUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deletePurchasedUSerById(@PathVariable("userId") Long userId){

        String message= purchasedRecordService.deletePurchasedUSerById(userId);

        return ResponseEntity.ok(message);

    }

}
