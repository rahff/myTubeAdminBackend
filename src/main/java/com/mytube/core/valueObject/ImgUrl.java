package com.mytube.core.valueObject;

public record ImgUrl(String value) {

  public ImgUrl {
    if(value.isEmpty())
      throw new RuntimeException();
  }
}
