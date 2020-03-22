package com.github.gustavovitor.photos.repository.album;

import com.github.gustavovitor.maker.repository.MongoRepositoryMaker;
import com.github.gustavovitor.photos.domain.album.Album;

public interface AlbumRepository extends MongoRepositoryMaker<Album, String> {
}
