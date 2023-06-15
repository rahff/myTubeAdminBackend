package com.mytube.core.port.driver;

import java.io.InputStream;
import java.util.Objects;

public record CreateVideoRequest(InputStream miniature, InputStream video, String description, String title, boolean reservedSubscriber) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CreateVideoRequest that)) return false;
    return Objects.equals(description, that.description)
      && Objects.equals(title, that.title)
      && Objects.equals(reservedSubscriber, that.reservedSubscriber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, reservedSubscriber);
  }
}
