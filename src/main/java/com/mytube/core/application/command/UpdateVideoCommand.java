package com.mytube.core.application.command;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.application.exceptions.UploadFileFailedException;
import com.mytube.core.port.driven.FileUploader;
import com.mytube.core.port.driven.VideoRepository;
import com.mytube.core.port.driver.UpdateVideoRequest;
import com.mytube.core.port.driver.ClientContext;

public class UpdateVideoCommand extends Command<UpdateVideoRequest> {
  private final FileUploader fileUploader;
  private final VideoRepository videoRepository;

  public UpdateVideoCommand(FileUploader fileUploader, VideoRepository videoRepository) {
    this.fileUploader = fileUploader;
    this.videoRepository = videoRepository;
  }

  public void exec(UpdateVideoRequest request, ClientContext ctx) throws UploadFileFailedException {
    AdminGuard.against(ctx);
    var videoMetaData = videoRepository.findById(request.videoId()).orElseThrow();
    fileUploader.replaceFile(request.updatedVideo(), videoMetaData.videoUrl());
  }
}
