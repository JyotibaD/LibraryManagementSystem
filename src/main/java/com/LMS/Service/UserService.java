package com.LMS.Service;

import com.LMS.Entity.User;

import java.util.Optional;

public interface UserService {
    public User getUserByID(Long id);

   public String createUser(User user);

    public Optional<User> getUserByName(String userName);

    public Optional<User> updateUser(Long id,User user);

    public String deleteUser(Long id);
}
