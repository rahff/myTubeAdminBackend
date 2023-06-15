package com.mytube.infra.gateway.provider.fileStorage.fake;

import com.mytube.core.application.exceptions.UploadFileFailedException;
import com.mytube.core.port.driven.FileUploader;
import com.mytube.infra.stub.Stub;

import java.io.InputStream;


public class InMemoryFileUploader extends Stub<InMemoryFileUploader> implements FileUploader {
  public InMemoryFileUploader() {
    super(InMemoryFileUploader.class);
  }
  public String uploadFile(InputStream file) throws UploadFileFailedException {
    registerMethodCall("uploadFile", "file");
    return "fakeUrl";
  }

  public void deleteFile(String resourceUrl) {
    registerMethodCall("deleteFile", resourceUrl);
  }


  public String replaceFile(InputStream file, String resourceUrl) throws UploadFileFailedException {
    registerMethodCall("replaceFile", resourceUrl);
    return resourceUrl+"/updated";
  }
}
