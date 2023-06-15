package com.mytube.core.valueObject;

public record VideoDescription(String value) {
  public VideoDescription {
    if(value.isEmpty())
      throw new RuntimeException();
  }
}
