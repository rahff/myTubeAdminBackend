package com.mytube.core.port.driver;

import java.util.Objects;

public record UpdateVideoMiniature(String id, String miniatureUrl, String description, String title) {
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UpdateVideoMiniature that)) return false;
    return Objects.equals(id, that.id)
      && Objects.equals(miniatureUrl, that.miniatureUrl)
      && Objects.equals(description, that.description)
      && Objects.equals(title, that.title);
  }

  public int hashCode() {
    return Objects.hash(id, miniatureUrl, description, title);
  }

  public static UpdateVideoMiniature from(UpdateVideoMetaDataRequest request, String newMiniature) {
    return new UpdateVideoMiniature(request.id(), newMiniature, request.description(), request.title());
  }

  public static UpdateVideoMiniature from(UpdateVideoMetaDataRequest request) {
    return new UpdateVideoMiniature(request.id(), null, request.description(), request.title());
  }

}
