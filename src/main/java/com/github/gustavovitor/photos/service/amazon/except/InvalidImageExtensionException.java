package com.github.gustavovitor.photos.service.amazon.except;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidImageExtensionException extends RuntimeException {

    List<String> validExtensions;

    public InvalidImageExtensionException(List<String> validExtensions) {
        this.validExtensions = validExtensions;
    }
}
