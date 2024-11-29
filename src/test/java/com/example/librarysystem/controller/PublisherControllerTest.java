package com.example.librarysystem.controller;

import com.example.librarysystem.model.Publisher;
import com.example.librarysystem.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherService publisherService;

    private Publisher publisher;

    @BeforeEach
    void setUp() {
        publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("Publisher Name");
    }

    @Test
    void createPublisher() throws Exception {
        when(publisherService.savePublisher(any(Publisher.class))).thenReturn(publisher);

        mockMvc.perform(post("/publishers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Publisher Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Publisher Name"));

        verify(publisherService, times(1)).savePublisher(any(Publisher.class));
    }

    @Test
    void getAllPublishers() throws Exception {
        List<Publisher> publishers = Arrays.asList(publisher);
        when(publisherService.getAllPublishers()).thenReturn(publishers);

        mockMvc.perform(get("/publishers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(publishers.size()))
                .andExpect(jsonPath("$[0].name").value("Publisher Name"));

        verify(publisherService, times(1)).getAllPublishers();
    }

    @Test
    void getPublisherById() throws Exception {
        when(publisherService.getPublisherById(1L)).thenReturn(publisher);

        mockMvc.perform(get("/publishers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Publisher Name"));

        verify(publisherService, times(1)).getPublisherById(1L);
    }

    @Test
    void updatePublisher() throws Exception {
        when(publisherService.savePublisher(any(Publisher.class))).thenReturn(publisher);

        mockMvc.perform(put("/publishers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Publisher Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Publisher Name"));

        verify(publisherService, times(1)).savePublisher(any(Publisher.class));
    }

    @Test
    void deletePublisher() throws Exception {
        doNothing().when(publisherService).deletePublisher(1L);

        mockMvc.perform(delete("/publishers/1"))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).deletePublisher(1L);
    }
}
