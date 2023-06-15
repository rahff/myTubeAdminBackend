package com.mytube.infra.gateway.repository.fake;

import com.mytube.core.port.driven.VideoMetaDataQuery;
import com.mytube.core.port.dto.VideoMiniatureDto;
import com.mytube.infra.stub.Stub;

import java.util.ArrayList;
import java.util.List;

public class InMemoryVideoQuery extends Stub<InMemoryVideoQuery> implements VideoMetaDataQuery {
  private final List<VideoMiniatureDto> data;

  public InMemoryVideoQuery() {
    super(InMemoryVideoQuery.class);
    this.data = new ArrayList<>();
  }

  public List<VideoMiniatureDto> getMiniaturePage(Integer page) {
    return data;
  }

  public boolean isPrivateVideo(String videoId) {
    return videoId.equals("privateVideoIdentifier");
  }
}
