package com.LMS.Service;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.User;
import com.LMS.Exception.BookAllreadyPresentException;
import com.LMS.Exception.ResourceNotFoundException;
import com.LMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User getUserByID(Long id) {

        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User NOT Found with Id:"+id));

    }

    @Override
    public String createUser(User user) {

        Optional<User> user1=userRepository.findByUserName(user.getUserName());
        if (user1.isPresent())
            throw new BookAllreadyPresentException("Username is not available"+user.getUserName());

        userRepository.save(user);

        return "Welcome to LMS "+user.getUserName();

    }

    public Optional<User> getUserByName(String userName){

        Optional<User> user=userRepository.findByUserName(userName);

        if(user.isEmpty())
            throw new ResourceNotFoundException("USer not found with Name: "+userName);


        return user;

    }

    @Override
    public Optional<User> updateUser(Long id,User user) {

        Optional<User> user1=userRepository.findById(id);
        if(user1.isEmpty())
            throw new ResourceNotFoundException("User not found with Id: "+user.getId());

        user1.get().setUserName(user.getUserName());
        user1.get().setEmail(user.getEmail());
        user1.get().setMobile(user.getMobile());
        user1.get().setAddress(user.getAddress());
        user1.get().setRole(user.getRole());

        userRepository.save(user1.get());

        return user1;
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty())
            throw new ResourceNotFoundException("User not found with userId: "+id);

        userRepository.deleteById(id);


        return user.get().getUserName()+" deleted successfully";
    }
}
