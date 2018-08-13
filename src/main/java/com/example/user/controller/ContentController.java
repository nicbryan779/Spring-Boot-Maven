package com.example.user.controller;

import com.example.user.exception.ResourceNotFoundException;
import com.example.user.model.Content;
import com.example.user.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContentController {

    @Autowired
    ContentRepository ContentRepository;

    // Get All Content
    @GetMapping("/content")
    public List<Content> getAllContents() {
        return ContentRepository.findAll();
    }

    // Create a new content
    @PostMapping("/content")
    public Content createContent(@Valid @RequestBody Content Content) {
        return ContentRepository.save(Content);
    }

    // Get email value
    @GetMapping("/email")
    public Content getEmail(){
        return ContentRepository.findEmail();
    }

    // Get email value
    @GetMapping("/address")
    public Content getAddress(){
        return ContentRepository.findAddress();
    }

    // Get email value
    @GetMapping("/phone")
    public Content getPhone(){
        return ContentRepository.findPhone();
    }

    // Update a Content
    @PutMapping("/content/{id}")
    public Content updateContent(@PathVariable(value = "id") Long ContentId,
                           @Valid @RequestBody Content ContentDetails) {

        Content Content = ContentRepository.findById(ContentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content", "id", ContentId));

        Content.setTitle(ContentDetails.getTitle());
        Content.setContent(ContentDetails.getContent());

        Content updatedContent = ContentRepository.save(Content);
        return updatedContent;
    }

    // Delete a Content
    @DeleteMapping("/content/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable(value = "id") Long ContentId) {
        Content Content = ContentRepository.findById(ContentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content", "id", ContentId));

        ContentRepository.delete(Content);

        return ResponseEntity.ok().build();
    }
}
