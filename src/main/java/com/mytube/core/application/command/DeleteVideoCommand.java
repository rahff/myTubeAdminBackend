package com.mytube.core.application.command;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.port.driven.FileUploader;
import com.mytube.core.port.driven.VideoRepository;
import com.mytube.core.port.driver.DeleteVideoRequest;
import com.mytube.core.port.driver.ClientContext;


public class DeleteVideoCommand extends Command<DeleteVideoRequest>{

  private final VideoRepository miniatureRepository;
  private final FileUploader fileUploader;
  public DeleteVideoCommand(VideoRepository miniatureRepository, FileUploader fileUploader) {
    this.miniatureRepository = miniatureRepository;
    this.fileUploader = fileUploader;
  }

  public void exec(DeleteVideoRequest request, ClientContext ctx) throws Exception {
    AdminGuard.against(ctx);
    var foundedVideo = miniatureRepository.findById(request.videoId()).orElseThrow();
    fileUploader.deleteFile(foundedVideo.imgUrl());
    fileUploader.deleteFile(foundedVideo.videoUrl());
    miniatureRepository.deleteMiniature(foundedVideo.id());
  }
}
