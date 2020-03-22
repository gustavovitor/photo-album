package com.github.gustavovitor.photos.service.album;

import com.github.gustavovitor.maker.service.MongoServiceMaker;
import com.github.gustavovitor.photos.domain.album.Album;
import com.github.gustavovitor.photos.repository.album.AlbumRepository;
import com.github.gustavovitor.photos.repository.album.spec.AlbumSpecification;
import org.springframework.stereotype.Service;

@Service
public class AlbumService extends MongoServiceMaker<AlbumRepository, Album, String, Album, AlbumSpecification> {
    @Override
    public void beforeInsert(Album object) {
        // Business rules here.
    }

    @Override
    public void beforeUpdate(String objectId, Album object) {
        // Business rules here.
    }

    @Override
    public void beforePatch(Album object) {
        // Business rules here.
    }

    @Override
    public void beforeDelete(String objectId) {
        // Business rules here.
    }
}
