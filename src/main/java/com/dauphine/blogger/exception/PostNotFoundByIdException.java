package com.dauphine.blogger.exception;

import java.util.UUID;

public class PostNotFoundByIdException extends  Exception {
    public PostNotFoundByIdException(UUID id) {
        super("Category with id " + id + " not found");
    }
}
