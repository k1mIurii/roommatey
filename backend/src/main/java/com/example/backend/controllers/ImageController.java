package com.example.backend.controllers;

import com.example.backend.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private static final String PROFILE_BUCKET = "profiles/";
    private static final String LISTING_BUCKET = "listings/";

    @GetMapping(path = "/profile/{image}")
    public ResponseEntity<InputStreamResource> getProfileImage(@PathVariable String image) {
        InputStream objectStream = this.imageService.getProfileImage(PROFILE_BUCKET,image);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(objectStream));
    }

    @GetMapping(path = "/profile/{image}")
    public ResponseEntity<InputStreamResource> getListingImage(@PathVariable String image) {
        InputStream objectStream = this.imageService.getListingImage(LISTING_BUCKET,image);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(objectStream));
    }

    @PostMapping(path = "/profile/upload/{id}")
    public ResponseEntity<?> uploadProfileImages(@PathVariable("id") Long id,
                                                 @RequestParam("images") List<MultipartFile> images) {
        this.imageService.uploadProfileImages(id, images);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/listing/upload/{id}")
    public ResponseEntity<?> uploadListingImages(@PathVariable("id") Long id,
                                                 @RequestParam("images") List<MultipartFile> images) {
        this.imageService.uploadListingImages(id, images);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{image}")
    public ResponseEntity<?> deleteImage(@PathVariable String image) {
        this.imageService.delete(image);
        return ResponseEntity.ok().build();
    }
}
