package com.LMS.Service;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.PurchasedRecord;
import com.LMS.Exception.ResourceNotFoundException;
import com.LMS.Repository.AdminRepository;
import com.LMS.Repository.PurchasedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<BookRecord> bookRecords=adminRepository.findAllByBookName(bookName);
        if(bookRecords.isEmpty())
            throw new ResourceNotFoundException("Books are not found with name: "+bookName);

        return bookRecords;
    }

    @Override
    public String barrowBook(PurchasedRecord purchasedRecord) {

        //Checking book is currently available or not
        Optional<BookRecord> bookRecordCheck= adminRepository.findByBookName(purchasedRecord.getPurchasedBookName());
        System.out.println(bookRecordCheck.toString());

        if(bookRecordCheck.isPresent()) {
            BookRecord bookRecord = bookRecordCheck.get();
            if(bookRecord.getCopies()== 0)
                throw new ResourceNotFoundException("Invalid input or Book copies unavailable");
        }


        //only one book allowed at a time
        String userName=purchasedRecord.getUserName();
        PurchasedRecord purchasedRecord1=purchasedRecordRepository.findByUserName(userName);

        if(purchasedRecord1 != null){
            return "You already borrowed 1 book : "+purchasedRecord1.getPurchasedBookName();
        }
        else {
            //here implemented when user borrowing 1 book then reducing copies by 1
            BookRecord bookRecord=adminRepository.findByBookId(purchasedRecord.getPurchasedBookId());
            int copies=bookRecord.getCopies();
            if(copies > 0 ){
                purchasedRecordRepository.save(purchasedRecord);
                copies = copies-1;
                bookRecord.setCopies(copies);
                adminService.updateBook(bookRecord.getBookId(),bookRecord);
                return "Happy Learning with : "+purchasedRecord.getPurchasedBookName();
            } else{
                return "No copies available currently";
            }
        }
    }

    @Override
    public List<PurchasedRecord> getAllBorrowedRecord() {
        return purchasedRecordRepository.findAll();
    }

    @Override
    public PurchasedRecord findByUserName(String userName) {
        PurchasedRecord purchasedRecord= purchasedRecordRepository.findByUserName(userName);
        if(purchasedRecord==null)
            throw new ResourceNotFoundException("Purchased User not found with Name: "+ userName);

        return purchasedRecordRepository.findByUserName(userName);
    }

    @Override
    public String deletePurchasedUSerById(Long userId) {

        Optional<PurchasedRecord> purchasedRecordCheck=purchasedRecordRepository.findById(userId);
        String message = null;

        if(purchasedRecordCheck.isPresent()){
            PurchasedRecord purchasedRecord=purchasedRecordCheck.get();
            message= "User "+purchasedRecord.getUserName()+" is deleted successfully";
        }

        if(purchasedRecordCheck.isEmpty())
            throw new ResourceNotFoundException(userId+" is not present in purchased record");

        purchasedRecordRepository.deleteById(userId);

        return message;
    }
}
