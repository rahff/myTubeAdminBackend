package com.mytube.core.port.driver;

import java.util.Objects;

public record DeleteVideoRequest(String videoId) {
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DeleteVideoRequest that)) return false;
    return Objects.equals(videoId, that.videoId);
  }

  public int hashCode() {
    return Objects.hash(videoId);
  }
}
