package com.LMS.Service;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;
import com.LMS.Repository.AdminRepository;
import com.LMS.Repository.PurchasedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedRecordsImpl implements PurchasedRecordService {
    @Autowired
    public AdminService adminService;
    @Autowired
    public AdminRepository adminRepository;
    @Autowired
    public PurchasedRecordRepository purchasedRecordRepository;

    @Override
    public List<BookRecord> getAllBooksByName(String bookName) {
        return adminRepository.findAllByBookName(bookName);
    }

    @Override
    public String barrowBook(PurchasedRecord purchasedRecord) {
        //only one book allowed at a time
        String userName=purchasedRecord.getUserName();
        PurchasedRecord purchasedRecord1=purchasedRecordRepository.findByUserName(userName);
        if(purchasedRecord1 != null){
            return "You allready borrowed 1 book : "+purchasedRecord1.getPurchasedBookName();
        }
        else {
            //here implemented when book borrowed then reducing copies by 1
            BookRecord bookRecord=adminRepository.findByBookId(purchasedRecord.getPurchasedBookId());
            int copies=bookRecord.getCopies();
            if(0<copies){
                purchasedRecordRepository.save(purchasedRecord);
                copies-=1;
                bookRecord.setCopies(copies);
                adminService.updateBook(bookRecord.getBookId(),bookRecord);
                return "Happy Learning with : "+purchasedRecord.getPurchasedBookName();
            }
            else{
                return "No copies available currently";
            }

        }

    }

    @Override
    public List<PurchasedRecord> getAllBorrowedRecord() {
        return purchasedRecordRepository.findAll();
    }
}
