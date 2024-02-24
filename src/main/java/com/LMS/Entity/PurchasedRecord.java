package com.LMS.Entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="purchasedRecords")
public class PurchasedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String userName;
    private long mobile;
    private  String address;
    private long purchasedBookId;
    private String purchasedBookName;

}
