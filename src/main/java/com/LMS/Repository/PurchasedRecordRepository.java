package com.LMS.Repository;

import com.LMS.Entity.PurchasedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedRecordRepository extends JpaRepository<PurchasedRecord,Long> {
   public PurchasedRecord findByUserName(String userName);
}
