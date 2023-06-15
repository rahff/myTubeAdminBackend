package com.mytube.infra.gateway.repository.fake;

import com.mytube.core.port.driven.VideoRepository;
import com.mytube.core.port.dto.VideoMiniatureDto;
import com.mytube.infra.stub.Stub;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryVideoRepository extends Stub<InMemoryVideoRepository> implements VideoRepository {
  private final List<VideoMiniatureDto> data;
  public InMemoryVideoRepository() {
    super(InMemoryVideoRepository.class);
    this.data = new ArrayList<>();
    data.add(new VideoMiniatureDto("1", "fakeImgUrl", "fakeVideoUrl", "fakeDescription", "title", true));
  }
  public void addVideoMetaData(VideoMiniatureDto miniature) {
    data.add(miniature);
    registerMethodCall("addVideoMetaData", miniature);
  }
  public void saveVideoMetaData(VideoMiniatureDto videoMiniatureDto) {
    registerMethodCall("saveVideoMetaData", videoMiniatureDto);
  }
  public Optional<VideoMiniatureDto> findById(String id) {
    registerMethodCall("findById", id);
    return data.stream().filter((m) -> m.id().equals(id)).findFirst();
  }
  public void deleteMiniature(String id) {
    registerMethodCall("deleteMiniature", id);
  }
}
