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
        purchasedRecordRepository.save(purchasedRecord);
        return "Happy Learning...";
    }

    @Override
    public List<PurchasedRecord> getAllBorrowedRecord() {
        return purchasedRecordRepository.findAll();
    }
}
