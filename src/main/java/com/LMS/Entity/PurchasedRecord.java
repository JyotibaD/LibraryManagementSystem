package com.LMS.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Mobile Number is mandatory")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Mobile number is not valid")
    private String mobile;

    @NotBlank(message = "address is mandatory")
    @Size(min = 10,max = 100,message = "Enter valid address")
    private  String address;

    @NotNull(message = "Book Id is mandatory")
    private long purchasedBookId;

    @NotBlank(message = "Book Name is mandatory")
    @Size(min = 3,max = 50,message = "Book Name must be between 3 and 50 characters")
    private String purchasedBookName;

}
