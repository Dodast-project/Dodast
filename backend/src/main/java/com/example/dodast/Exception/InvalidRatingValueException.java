package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class InvalidRatingValueException extends ApiException {
  public InvalidRatingValueException() {
    super("امتیاز باید بین 1 تا 5 باشد.", HttpStatus.BAD_REQUEST);
  }
}
