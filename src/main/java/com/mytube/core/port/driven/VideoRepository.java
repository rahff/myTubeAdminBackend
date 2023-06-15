package com.mytube.core.port.driven;

import com.mytube.core.port.dto.VideoMiniatureDto;

import java.util.Optional;

public interface VideoRepository {
  void addVideoMetaData(VideoMiniatureDto miniature);
  void saveVideoMetaData(VideoMiniatureDto videoMiniatureDto);
  Optional<VideoMiniatureDto> findById(String id);

  void deleteMiniature(String id);
}
