package com.LMS.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "UserRecords")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Mobile Number is mandatory")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Mobile number is not valid")
    private String mobile;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "address is mandatory")
    @Size(min = 10,max = 100,message = "Enter valid address")
    private  String address;

    @Size(min = 3,max = 100,message = "Enter valid address")
    private String role;

    public User(String userName, String mobile, String email, String address, String role) {
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.role = role;
    }
}
