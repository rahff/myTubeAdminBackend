package com.mytube.core.port.driver;

import java.util.Objects;

public record CreateVideoMetaDataRequest(String imgUrl, String videoUrl, String description, String title, boolean reservedSubscriber) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CreateVideoMetaDataRequest that)) return false;
    return Objects.equals(imgUrl, that.imgUrl)
      && Objects.equals(videoUrl, that.videoUrl)
      && Objects.equals(description, that.description)
      && Objects.equals(reservedSubscriber, that.reservedSubscriber)
      && Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imgUrl, videoUrl, description, title, reservedSubscriber);
  }
}
