package com.mytube.core.valueObject;

public record VideoUrl(String value) {
  public VideoUrl {
    if(value.isEmpty())
      throw new RuntimeException();
  }
}
