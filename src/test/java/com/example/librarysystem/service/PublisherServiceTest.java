package com.example.librarysystem.service;

import com.example.librarysystem.exception.ResourceNotFoundException;
import com.example.librarysystem.model.Publisher;
import com.example.librarysystem.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private Publisher publisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("Publisher Name");
    }

    @Test
    void savePublisher() {
        when(publisherRepository.save(publisher)).thenReturn(publisher);

        Publisher savedPublisher = publisherService.savePublisher(publisher);

        assertNotNull(savedPublisher);
        assertEquals(publisher.getName(), savedPublisher.getName());
        verify(publisherRepository, times(1)).save(publisher);
    }

    @Test
    void getAllPublishers() {
        when(publisherRepository.findAll()).thenReturn(Arrays.asList(publisher));

        List<Publisher> publishers = publisherService.getAllPublishers();

        assertNotNull(publishers);
        assertEquals(1, publishers.size());
        verify(publisherRepository, times(1)).findAll();
    }

    @Test
    void getPublisherById() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher));

        Publisher foundPublisher = publisherService.getPublisherById(1L);

        assertNotNull(foundPublisher);
        assertEquals(publisher.getName(), foundPublisher.getName());
        verify(publisherRepository, times(1)).findById(1L);
    }

    @Test
    void getPublisherById_NotFound() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows (ResourceNotFoundException.class, () -> {
            publisherService.getPublisherById(1L);
        });

        assertEquals("Publisher not found with id: 1", exception.getMessage());
        verify(publisherRepository, times(1)).findById(1L);
    }

    @Test
    void deletePublisher() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher));
        doNothing().when(publisherRepository).deleteById(1L);

        publisherService.deletePublisher(1L);

        verify(publisherRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletePublisher_NotFound() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows (ResourceNotFoundException.class, () -> {
            publisherService.deletePublisher(1L);
        });

        assertEquals("Publisher not found with id: 1", exception.getMessage());
        verify(publisherRepository, times(1)).findById(1L);
    }
}
