package com.mytube.core.application.command;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.application.exceptions.UploadFileFailedException;
import com.mytube.core.entities.Video;
import com.mytube.core.port.driven.FileUploader;
import com.mytube.core.port.driven.VideoRepository;
import com.mytube.core.port.driver.ClientRole;
import com.mytube.core.port.driver.UpdateVideoMiniature;
import com.mytube.core.port.driver.UpdateVideoMetaDataRequest;
import com.mytube.core.port.driver.ClientContext;

public class UpdateVideoMetaDataCommand extends Command<UpdateVideoMetaDataRequest> {

  private final VideoRepository repository;
  private final FileUploader fileUploader;

  public UpdateVideoMetaDataCommand(VideoRepository repository, FileUploader fileUploader) {
    this.repository = repository;
    this.fileUploader = fileUploader;
  }
  public void exec(UpdateVideoMetaDataRequest request, ClientContext ctx) throws UploadFileFailedException {
    AdminGuard.against(ctx);
    var foundedMiniature = repository.findById(request.id()).orElseThrow();
    var videoMiniature = Video.fromDto(foundedMiniature);
    if(request.miniature() != null){
      var newMiniatureUrl = fileUploader.replaceFile(request.miniature(), foundedMiniature.imgUrl());
      var updateMiniatureRequest = UpdateVideoMiniature.from(request, newMiniatureUrl);
      videoMiniature.makeUpdate(updateMiniatureRequest);
    }else {
      var updateMiniatureRequest = UpdateVideoMiniature.from(request);
      videoMiniature.makeUpdate(updateMiniatureRequest);
    }
    repository.saveVideoMetaData(videoMiniature.asDto());
  }
}
