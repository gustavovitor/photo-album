package com.github.gustavovitor.photos.service.amazon;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.github.gustavovitor.photos.domain.amazon.AmazonImage;
import com.github.gustavovitor.photos.repository.amazon.AmazonImageRepository;
import com.github.gustavovitor.photos.service.amazon.except.FileConversionException;
import com.github.gustavovitor.photos.service.amazon.except.InvalidImageExtensionException;
import com.github.gustavovitor.photos.service.amazon.util.AmazonClientService;
import com.github.gustavovitor.photos.util.FileUtils;
import com.github.gustavovitor.util.MessageUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class AmazonS3ImageService extends AmazonClientService {

    @Autowired
    private AmazonImageRepository amazonImageRepository;

    // Upload a List of Images to AWS S3.
    public List<AmazonImage> insertImages(List<MultipartFile> images) {
        List<AmazonImage> amazonImages = new ArrayList<>();
        images.forEach(image -> amazonImages.add(uploadImageToAmazon(image)));
        return amazonImages;
    }

    // Upload image to AWS S3.
    public AmazonImage uploadImageToAmazon(MultipartFile multipartFile) {

        // Valid extensions array, like jpeg/jpg and png.
        List<String> validExtensions = Arrays.asList("jpeg", "jpg", "png");

        // Get extension of MultipartFile
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!validExtensions.contains(extension)) {
            // If file have a invalid extension, call an Exception.
            log.warn(MessageUtil.getMessage("invalid.image.extesion"));
            throw new InvalidImageExtensionException(validExtensions);
        } else {

            // Upload file to Amazon.
            String url = uploadMultipartFile(multipartFile);

            // Save image information on MongoDB and return them.
            AmazonImage amazonImage = new AmazonImage();
            amazonImage.setImageUrl(url);

            return amazonImageRepository.insert(amazonImage);
        }

    }

    public void removeImageFromAmazon(AmazonImage amazonImage) {
        String fileName = amazonImage.getImageUrl().substring(amazonImage.getImageUrl().lastIndexOf("/") + 1);
        getClient().deleteObject(new DeleteObjectRequest(getBucketName(), fileName));
        amazonImageRepository.delete(amazonImage);
    }

    // Make upload to Amazon.
    private String uploadMultipartFile(MultipartFile multipartFile) {
        String fileUrl;

        try {
            // Get the file from MultipartFile.
            File file = FileUtils.convertMultipartToFile(multipartFile);

            // Extract the file name.
            String fileName = FileUtils.generateFileName(multipartFile);

            // Upload file.
            uploadPublicFile(fileName, file);

            // Delete the file and get the File Url.
            file.delete();
            fileUrl = getUrl().concat(fileName);
        } catch (IOException e) {

            // If IOException on conversion or any file manipulation, call exception.
            log.warn(MessageUtil.getMessage("multipart.to.file.convert.except"), e);
            throw new FileConversionException();
        }

        return fileUrl;
    }

    // Send image to AmazonS3, if have any problems here, the image fragments are removed from amazon.
    // Font: https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/s3/AmazonS3Client.html#putObject%28com.amazonaws.services.s3.model.PutObjectRequest%29
    private void uploadPublicFile(String fileName, File file) {
        getClient().putObject(new PutObjectRequest(getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

}
