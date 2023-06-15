package com.mytube.core.port.driven;

import com.mytube.core.application.exceptions.DeleteFileFailedException;
import com.mytube.core.application.exceptions.UploadFileFailedException;

import java.io.InputStream;

public interface FileUploader {
  String uploadFile(InputStream file) throws UploadFileFailedException;

  void deleteFile(String resourceUrl) throws DeleteFileFailedException;

  String replaceFile(InputStream file, String resourceUrl) throws UploadFileFailedException;
}
