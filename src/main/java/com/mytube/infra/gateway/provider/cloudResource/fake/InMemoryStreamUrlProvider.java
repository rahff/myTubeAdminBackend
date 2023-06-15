package com.mytube.infra.gateway.provider.cloudResource.fake;

import com.mytube.core.port.driven.StreamUrlProvider;

public class InMemoryStreamUrlProvider implements StreamUrlProvider {

  public String getSignedUrl(String resourceIdentifier) {
    return resourceIdentifier+"/fakeSignedUrl";
  }
}
