package com.mytube.core.port.driver;

import java.io.InputStream;
import java.util.Objects;

public record UpdateVideoRequest(String videoId, InputStream updatedVideo) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UpdateVideoRequest that)) return false;
    return Objects.equals(videoId, that.videoId)
      && Objects.equals(updatedVideo, that.updatedVideo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(videoId, updatedVideo);
  }
}
