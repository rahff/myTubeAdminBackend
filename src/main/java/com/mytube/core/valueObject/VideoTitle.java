package com.mytube.core.valueObject;

public record VideoTitle(String value) {
  public VideoTitle {
    if(value.isEmpty()) throw new RuntimeException("invalid title");
  }
}
