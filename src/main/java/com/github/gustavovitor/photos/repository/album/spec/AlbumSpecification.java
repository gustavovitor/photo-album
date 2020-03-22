package com.github.gustavovitor.photos.repository.album.spec;

import com.github.gustavovitor.maker.repository.MongoSpecificationBase;
import com.github.gustavovitor.photos.domain.album.Album;
import com.github.gustavovitor.photos.domain.album.QAlbum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import javax.management.ReflectionException;

import static java.util.Objects.nonNull;

public class AlbumSpecification extends MongoSpecificationBase<Album> {
    public AlbumSpecification(Album object) throws ReflectionException {
        super(object);
    }

    @Override
    public Predicate toPredicate() {
        BooleanBuilder builder = new BooleanBuilder();
        if (nonNull(getObject().getTitle())) {
            builder.and(QAlbum.album.title.containsIgnoreCase(getObject().getTitle()));
        }
        return builder;
    }
}
