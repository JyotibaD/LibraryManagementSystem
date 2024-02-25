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
    public AdminRepository adminRepository;
    @Autowired
    public PurchasedRecordRepository purchasedRecordRepository;

    @Override
    public List<BookRecord> getAllBooksByName(String bookName) {
        return adminRepository.findAllByBookName(bookName);
    }

    @Override
    public String barrowBook(PurchasedRecord purchasedRecord) {
        String userName=purchasedRecord.getUserName();
        PurchasedRecord purchasedRecord1=purchasedRecordRepository.findByUserName(userName);
        if(purchasedRecord1 != null){
            return "You allready borrowed 1 book : "+purchasedRecord1.getPurchasedBookName();
        }
        else {
            purchasedRecordRepository.save(purchasedRecord);
            return "Happy Learning with : "+purchasedRecord.getPurchasedBookName();
        }

    }

    @Override
    public List<PurchasedRecord> getAllBorrowedRecord() {
        return purchasedRecordRepository.findAll();
    }
}
