package com.github.gustavovitor.photos.resource.album;

import com.github.gustavovitor.maker.resource.MongoResourceMaker;
import com.github.gustavovitor.photos.domain.album.Album;
import com.github.gustavovitor.photos.service.album.AlbumService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumResource extends MongoResourceMaker<AlbumService, Album, String, Album> {

}
