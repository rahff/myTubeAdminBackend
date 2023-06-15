package com.mytube.core.port.driver;

import java.io.InputStream;
import java.util.Objects;

public record UpdateVideoMetaDataRequest(String id, InputStream miniature, String description, String title) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UpdateVideoMetaDataRequest that)) return false;
    return Objects.equals(id, that.id)
      && Objects.equals(description, that.description)
      && Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, title);
  }
}
