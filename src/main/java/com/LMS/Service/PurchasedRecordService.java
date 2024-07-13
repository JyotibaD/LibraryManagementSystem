package com.LMS.Service;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;

import java.util.List;

public interface PurchasedRecordService {
    public List<BookRecord> getAllBooksByName(String bookName);
    public String barrowBook(PurchasedRecord purchasedRecord);
    public List<PurchasedRecord> getAllBorrowedRecord();
    public PurchasedRecord findByUserName(String userName);
}
