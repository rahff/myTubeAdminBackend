package com.mytube.core.application.query;

import com.mytube.core.port.driven.StreamUrlProvider;
import com.mytube.core.port.driven.VideoMetaDataQuery;
import com.mytube.core.port.driver.ClientContext;


public class VideoStreamUrlQuery extends Query<String, String>  {

  private final StreamUrlProvider streamUrlProvider;
  private final VideoMetaDataQuery videoMetaDataDao;

  public VideoStreamUrlQuery(StreamUrlProvider streamUrlProvider, VideoMetaDataQuery videoMetaDataDao) {
    this.streamUrlProvider = streamUrlProvider;
    this.videoMetaDataDao = videoMetaDataDao;
  }

  public String getData(String resourceIdentifier, ClientContext ctx) throws Exception {
    var isReservedForSubscriber = videoMetaDataDao.isPrivateVideo(resourceIdentifier);
    SubscriberGuard.against(isReservedForSubscriber, ctx);
    return streamUrlProvider.getSignedUrl(resourceIdentifier);
  }
}
