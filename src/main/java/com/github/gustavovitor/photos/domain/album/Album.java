package com.github.gustavovitor.photos.domain.album;

import com.github.gustavovitor.photos.domain.amazon.AmazonImage;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document
public class Album {

    @Id
    private String albumId;

    @Size(max = 64)
    @NotNull
    @NotBlank
    private String title;

    // A list of AmazonImage, for this simple project, the AmazonImage is one request
    // and AlgumPhoto is another request.
    private List<AmazonImage> images;

}
