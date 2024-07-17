package com.Library;

import com.LMS.Entity.BookRecord;
import com.LMS.Exception.ResourceNotFoundException;
import com.LMS.Repository.AdminRepository;
import com.LMS.Service.AdminServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @BeforeEach
    public void setup() {
        // No need to initialize mocks manually
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookById_Success() {
        // Arrange
        Long bookId = 1l;
        BookRecord expectedBookRecord = new BookRecord();
        expectedBookRecord.setBookId(bookId);
        expectedBookRecord.setBookName("java");
        expectedBookRecord.setCopies(12);
        expectedBookRecord.setBookAuthor("Ajinkya");

        when(adminRepository.findById(bookId)).thenReturn(Optional.of(expectedBookRecord));

        // Act
        BookRecord actualBookRecord = adminService.getBookById(bookId);

        // Assert

    }

    @Test
    public void testGetBookById_NotFound() {
        // Arrange
        Long bookId = 1L;

        when(adminRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> adminService.getBookById(bookId));
    }
}
