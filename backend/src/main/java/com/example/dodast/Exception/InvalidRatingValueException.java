package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class InvalidRatingValueException extends ApiException {
  public InvalidRatingValueException() {
    super("Rating must be between 1 and 5.", HttpStatus.BAD_REQUEST);
  }
}
