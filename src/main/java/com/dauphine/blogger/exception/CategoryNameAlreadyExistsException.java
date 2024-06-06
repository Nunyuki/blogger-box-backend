package com.dauphine.blogger.exception;

import java.util.UUID;

public class CategoryNameAlreadyExistsException extends Exception {
    public CategoryNameAlreadyExistsException(String name) {
        super("Category with name " + name + " already exists");
    }
}
