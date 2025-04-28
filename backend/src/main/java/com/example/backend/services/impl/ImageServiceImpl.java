package com.example.backend.services.impl;

import com.example.backend.entities.Image;
import com.example.backend.entities.Listing;
import com.example.backend.entities.Profile;
import com.example.backend.exceptions.RecordNotFoundException;
import com.example.backend.repositories.jpa.ImageRepository;
import com.example.backend.services.ImageService;
import com.example.backend.services.ListingService;
import com.example.backend.services.ProfileService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final MinioClient minioClient;
    private final ProfileService profileService;
    private final ListingService listingService;
    private final ImageRepository imageRepository;

    private static final String PROFILE_BUCKET = "profiles/";
    private static final String LISTING_BUCKET = "listings/";

    @Override
    public void uploadProfileImages(Long id,List<MultipartFile> files) {
        Profile profile = this.profileService.getProfileById(id);

        List<String> links = files.stream().map(file -> {
            String link = PROFILE_BUCKET + id + file.getOriginalFilename();
            try {
                minioClient.putObject(
                  PutObjectArgs.builder()
                          .bucket(PROFILE_BUCKET)
                          .object(link)
                          .stream(file.getInputStream(), file.getSize(), -1)
                          .contentType(file.getContentType())
                          .build()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return link;
        }).toList();

        links.stream().map(e -> Image.builder().link(e).profile(profile).build()).forEach(imageRepository::save);
    }

    @Override
    public void uploadListingImages(Long id,List<MultipartFile> files) {
        Listing listing = this.listingService.getListingById(id);

        List<String> links = files.stream().map(file -> {
            String link = LISTING_BUCKET + id + file.getOriginalFilename();
            try {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(LISTING_BUCKET)
                                .object(link)
                                .stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return link;
        }).toList();

        links.stream().map(e -> Image.builder().link(e).listing(listing).build()).forEach(imageRepository::save);
    }

    @Override
    public void delete(String link) {
        Image image = this.imageRepository.findByLinkAndDeletedAtIsNull(link)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Image with link %s not found", link)));
        image.setDeletedAt(LocalDateTime.now());
        this.imageRepository.save(image);
    }

    @Override
    public List<String> getImagesByProfileId(Long profileId) {
        Profile profile = this.profileService.getProfileById(profileId);
        return imageRepository.findAllByProfileIdAndDeletedAtIsNull(profile.getId())
                .stream().map(Image::getLink).toList();
    }

    @Override
    public List<String> getImagesByListingId(Long listingId) {
        Listing listing = this.listingService.getListingById(listingId);
        return imageRepository.findAllByListingIdAndDeletedAtIsNull(listing.getId())
                .stream().map(Image::getLink).toList();
    }

    @SneakyThrows
    @Override
    public InputStream getProfileImage(String bucketName, String image) {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(image)
                        .build()
        );
    }

    @SneakyThrows
    @Override
    public InputStream getListingImage(String bucketName, String image) {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(image)
                        .build()
        );
    }

}
