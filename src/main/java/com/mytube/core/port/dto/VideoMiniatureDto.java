package com.mytube.core.port.dto;

import java.util.Objects;

public record VideoMiniatureDto(String id, String imgUrl, String videoUrl, String description, String title, boolean reservedSubscriber) {
  public String getId() {
    return id;
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof VideoMiniatureDto that)) return false;
    return Objects.equals(id, that.id)
      && Objects.equals(description, that.description)
      && Objects.equals(imgUrl, that.imgUrl)
      && Objects.equals(videoUrl, that.videoUrl)
      && Objects.equals(reservedSubscriber, that.reservedSubscriber)
      && Objects.equals(title, that.title);
  }

  public int hashCode() {
    return Objects.hash(id, description, imgUrl, videoUrl, title, reservedSubscriber);
  }
}
