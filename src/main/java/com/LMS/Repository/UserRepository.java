package com.LMS.Repository;

import com.LMS.Entity.BookRecord;
import com.LMS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByUserName(String userName);

}
