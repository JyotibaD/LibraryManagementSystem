package com.LMS.Service;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;

import java.util.List;

public interface PurchasedRecordService {
     List<BookRecord> getAllBooksByName(String bookName);
     String barrowBook(PurchasedRecord purchasedRecord);
     List<PurchasedRecord> getAllBorrowedRecord();
     PurchasedRecord findByUserName(String userName);
     String deletePurchasedUSerById(Long userId);
}
