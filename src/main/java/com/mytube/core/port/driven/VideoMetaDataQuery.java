package com.mytube.core.port.driven;

import com.mytube.core.port.dto.VideoMiniatureDto;

import java.util.List;

public interface VideoMetaDataQuery {
  List<VideoMiniatureDto> getMiniaturePage(Integer page);
  boolean isPrivateVideo(String videoId);
}
