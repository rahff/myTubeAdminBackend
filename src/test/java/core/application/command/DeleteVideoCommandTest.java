package core.application.command;

import com.mytube.core.application.command.DeleteVideoCommand;
import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.port.driver.ClientRole;
import com.mytube.core.port.driver.DeleteVideoRequest;
import com.mytube.infra.gateway.provider.fileStorage.fake.InMemoryFileUploader;
import com.mytube.infra.gateway.repository.fake.InMemoryVideoRepository;
import com.mytube.infra.security.context.fake.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteVideoCommandTest {
  InMemoryFileUploader fileUploader;
  InMemoryVideoRepository videoRepository;
  @BeforeEach
  void setup() {
    fileUploader = new InMemoryFileUploader();
    videoRepository = new InMemoryVideoRepository();
  }

  @Test
  void AnAdminUserDeleteVideoFromHisCollection() {

    var command = new DeleteVideoCommand(videoRepository, fileUploader);
    var request = new DeleteVideoRequest("1");
    var ctx = new TestContext(ClientRole.ADMIN);
    assertDoesNotThrow(() -> command.exec(request, ctx));
    assertTrue(fileUploader.toHaveBeenCallWith("deleteFile", "fakeVideoUrl"));
    assertTrue(fileUploader.toHaveBeenCallWith("deleteFile", "fakeImgUrl"));
    assertTrue(videoRepository.toHaveBeenCallWith("deleteMiniature", "1"));
  }

  @Test
  void AnNonAdminUserTriesToDeleteVideoFromHisCollection() {

    var command = new DeleteVideoCommand(videoRepository, fileUploader);
    var request = new DeleteVideoRequest("1");
    var ctx = new TestContext(ClientRole.SUBSCRIBER);
    assertThrows(PermissionDeniedException.class, () -> command.exec(request, ctx));
    assertFalse(fileUploader.toHaveBeenCall("deleteFile"));
    assertFalse(fileUploader.toHaveBeenCall("deleteFile"));
    assertFalse(videoRepository.toHaveBeenCall("deleteMiniature"));
  }
}
