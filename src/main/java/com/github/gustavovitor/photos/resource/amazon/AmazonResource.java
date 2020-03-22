package com.github.gustavovitor.photos.resource.amazon;

import com.github.gustavovitor.photos.domain.amazon.AmazonImage;
import com.github.gustavovitor.photos.service.amazon.AmazonS3ImageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@RestController
@RequestMapping("/amazon")
public class AmazonResource {

    @Autowired
    private AmazonS3ImageService amazonS3ImageService;

    @PostMapping("/images")
    public ResponseEntity<List<AmazonImage>> insertImages(@RequestPart(value = "images") List<MultipartFile> images) {
        return ResponseEntity.ok(getAmazonS3ImageService().insertImages(images));
    }

}