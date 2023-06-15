package com.mytube.core.application.command;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.application.exceptions.UploadFileFailedException;
import com.mytube.core.entities.Video;
import com.mytube.core.port.driven.FileUploader;
import com.mytube.core.port.driven.VideoRepository;
import com.mytube.core.port.driver.CreateVideoMetaDataRequest;
import com.mytube.core.port.driver.CreateVideoRequest;
import com.mytube.core.port.driver.ClientContext;


public class AddVideoCommand extends Command<CreateVideoRequest>{
  private final VideoRepository repository;
  private final FileUploader uploader;

  public AddVideoCommand(VideoRepository repository, FileUploader uploader) {
    this.repository = repository;
    this.uploader = uploader;
  }

  public void exec(CreateVideoRequest request, ClientContext ctx) throws UploadFileFailedException {
    AdminGuard.against(ctx);
    var videoUrl = uploader.uploadFile(request.video());
    var miniatureUrl = uploader.uploadFile(request.miniature());
    var createMiniatureRequest = new CreateVideoMetaDataRequest(miniatureUrl, videoUrl, request.description(), request.title(), request.reservedSubscriber());
    var videoMiniature = Video.fromCreateRequest(createMiniatureRequest);
    repository.addVideoMetaData(videoMiniature.asDto());
  }
}
