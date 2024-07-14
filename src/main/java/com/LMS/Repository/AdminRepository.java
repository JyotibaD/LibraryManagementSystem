package com.LMS.Repository;

import com.LMS.Entity.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<BookRecord,Long> {

    public Optional<BookRecord> findByBookName(String bookName);
    public BookRecord findByBookId(long bookId);

    public List<BookRecord> findAllByBookName(String bookName);

    public BookRecord findByBookAuthor(String authorName);

}
