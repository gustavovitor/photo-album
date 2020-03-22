package com.github.gustavovitor.photos.repository.amazon;

import com.github.gustavovitor.photos.domain.amazon.AmazonImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AmazonImageRepository extends MongoRepository<AmazonImage, String> {
}
