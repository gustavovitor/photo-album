package com.github.gustavovitor.photos.domain.amazon;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class AmazonImage {

    @Id
    private String amazonUserImageId;

    @NotNull
    private String imageUrl;

}
