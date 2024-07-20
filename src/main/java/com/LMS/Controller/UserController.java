package com.LMS.Controller;


import com.LMS.Entity.BookRecord;
import com.LMS.Entity.User;
import com.LMS.Exception.NotBlankException;
import com.LMS.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id ){

        if(id==null)
            throw new NotBlankException("UserId can not be null");

        User user=userService.getUserByID(id);

        return ResponseEntity.ok(user);

    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){
        if(user==null)
            throw new NotBlankException("Please enter user details");

        String meassage=userService.createUser(user);

        return  ResponseEntity.status(HttpStatus.CREATED).body(meassage);
    }

    @GetMapping("/userName")
    public ResponseEntity<Optional<User>> getUserByName(@RequestParam("userName") String userName){

        Optional<User> user= userService.getUserByName(userName);

        return ResponseEntity.ok(user);

    }

    @PutMapping("updateUser/{id}")
    private ResponseEntity<Optional<User>> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user ){
        if(user==null)
            throw new NotBlankException("Please enter user details");

        Optional<User> user1= userService.updateUser(id,user);

        return ResponseEntity.accepted().body(user1);

    }

    @DeleteMapping("/deleteUSer/{id}")
    public String deleteUser(@PathVariable Long id){
        if(id==null)
            throw new NotBlankException("UserId can not be null");

        return userService.deleteUser(id);
    }

}
