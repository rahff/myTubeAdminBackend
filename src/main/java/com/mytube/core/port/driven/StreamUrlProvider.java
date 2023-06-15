package com.mytube.core.port.driven;

public interface StreamUrlProvider {
  String getSignedUrl(String resourceIdentifier) throws Exception;
}
