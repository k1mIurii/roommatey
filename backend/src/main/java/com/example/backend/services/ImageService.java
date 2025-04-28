package com.example.backend.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ImageService {

    void uploadProfileImages(Long id,List<MultipartFile> files);

    void uploadListingImages(Long id,List<MultipartFile> files);

    void delete(String link);

    List<String> getImagesByProfileId(Long profileId);

    List<String> getImagesByListingId(Long listingId);

    InputStream getProfileImage(String bucketName, String image);

    InputStream getListingImage(String bucketName, String image);
}
