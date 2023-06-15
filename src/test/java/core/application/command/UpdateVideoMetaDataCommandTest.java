package core.application.command;

import com.mytube.core.application.command.UpdateVideoMetaDataCommand;
import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.port.driver.ClientRole;
import com.mytube.core.port.driver.UpdateVideoMetaDataRequest;
import com.mytube.core.port.dto.VideoMiniatureDto;
import com.mytube.infra.gateway.provider.fileStorage.fake.InMemoryFileUploader;
import com.mytube.infra.gateway.repository.fake.InMemoryVideoRepository;
import com.mytube.infra.security.context.fake.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateVideoMetaDataCommandTest {
  InMemoryVideoRepository repository;
  InMemoryFileUploader fileUploader;

  @BeforeEach
  void setup() {
    repository = new InMemoryVideoRepository();
    fileUploader = new InMemoryFileUploader();
  }

  @Test
  void AnAdminUserUpdateImgAndDescriptionForAParticularVideo() {
    var request = new UpdateVideoMetaDataRequest("1", InputStream.nullInputStream(), "newFakeDescription", "newTitle");
    var ctx = new TestContext(ClientRole.ADMIN);
    var command = new UpdateVideoMetaDataCommand(repository, fileUploader);
    var expectedResult = new VideoMiniatureDto("1", "fakeImgUrl/updated", "fakeVideoUrl", "newFakeDescription", "newTitle", true);

    assertDoesNotThrow(()-> command.exec(request, ctx));
    assertTrue(repository.toHaveBeenCallWith("saveVideoMetaData", expectedResult));
  }

  @Test
  void AnAdminUserUpdateDescriptionAndTitleForAParticularVideo() {
    var request = new UpdateVideoMetaDataRequest("1", null, "newFakeDescription", "newTitle");
    var ctx = new TestContext(ClientRole.ADMIN);
    var command = new UpdateVideoMetaDataCommand(repository, fileUploader);
    var expectedResult = new VideoMiniatureDto("1", "fakeImgUrl", "fakeVideoUrl", "newFakeDescription", "newTitle", true);

    assertDoesNotThrow(()-> command.exec(request, ctx));
    assertTrue(repository.toHaveBeenCallWith("saveVideoMetaData", expectedResult));
  }

  @Test
  void AnNonAdminUserTriesToUpdateDescriptionAndTitleForAParticularVideo() {
    var request = new UpdateVideoMetaDataRequest("1", null, "newFakeDescription", "newTitle");
    var ctx = new TestContext(ClientRole.SUBSCRIBER);
    var command = new UpdateVideoMetaDataCommand(repository, fileUploader);

    assertThrows(PermissionDeniedException.class, ()-> command.exec(request, ctx));
    assertFalse(repository.toHaveBeenCall("saveVideoMetaData"));
  }
}
