package com.mytube.core.entities;

import com.mytube.core.port.driver.CreateVideoMetaDataRequest;
import com.mytube.core.port.driver.UpdateVideoMiniature;
import com.mytube.core.port.dto.VideoMiniatureDto;
import com.mytube.core.valueObject.*;



public class Video {
  private final EntityId id;
  private ImgUrl imgUrl;
  private final VideoUrl videoUrl;
  private VideoDescription description;
  private VideoTitle title;
  private Boolean reservedSubscriber;

  private Video(CreateVideoMetaDataRequest request) {
    this.id = new EntityId(null);
    this.imgUrl = new ImgUrl(request.imgUrl());
    this.videoUrl = new VideoUrl(request.videoUrl());
    this.description = new VideoDescription(request.description());
    this.title = new VideoTitle(request.title());
    this.reservedSubscriber = request.reservedSubscriber();
  }

  private Video(VideoMiniatureDto dto) {
    this.id = new EntityId(dto.getId());
    this.imgUrl = new ImgUrl(dto.imgUrl());
    this.videoUrl = new VideoUrl(dto.videoUrl());
    this.description = new VideoDescription(dto.description());
    this.title = new VideoTitle(dto.title());
    this.reservedSubscriber = dto.reservedSubscriber();
  }

  public static Video fromCreateRequest(CreateVideoMetaDataRequest request) {
    return new Video(request);
  }

  public static Video fromDto(VideoMiniatureDto dto) {
    return new Video(dto);
  }

  public EntityId getId() {
    return id;
  }

  public ImgUrl getImgUrl() {
    return imgUrl;
  }

  public VideoUrl getVideoUrl() {
    return videoUrl;
  }

  public VideoDescription getDescription() {
    return description;
  }

  public VideoTitle getTitle() {
    return title;
  }

  public VideoMiniatureDto asDto() {
    return new VideoMiniatureDto(getId().value(), getImgUrl().value(), getVideoUrl().value(), getDescription().value(), getTitle().value(), reservedSubscriber);
  }

  public void makeUpdate(UpdateVideoMiniature request) {
    this.description = new VideoDescription(request.description());
    this.imgUrl = request.miniatureUrl() != null ? new ImgUrl(request.miniatureUrl()) : this.getImgUrl();
    this.title = new VideoTitle(request.title());
  }
}
