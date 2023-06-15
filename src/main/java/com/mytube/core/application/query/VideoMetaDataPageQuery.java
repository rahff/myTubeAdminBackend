package com.mytube.core.application.query;

import com.mytube.core.port.driven.VideoMetaDataQuery;
import com.mytube.core.port.driver.ClientContext;
import com.mytube.core.port.dto.VideoMiniatureDto;

import java.util.List;

public class VideoMetaDataPageQuery extends Query<String, List<VideoMiniatureDto>>{
  private final VideoMetaDataQuery repository;
  public VideoMetaDataPageQuery(VideoMetaDataQuery repository) {
    this.repository = repository;
  }
  public List<VideoMiniatureDto> getData(String page, ClientContext ctx) {
    return repository.getMiniaturePage(Integer.valueOf(page));
  }
}
