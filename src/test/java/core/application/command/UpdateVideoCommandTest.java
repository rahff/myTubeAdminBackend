package core.application.command;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.port.driver.ClientRole;
import com.mytube.core.port.driver.UpdateVideoRequest;
import com.mytube.infra.gateway.provider.fileStorage.fake.InMemoryFileUploader;
import com.mytube.infra.gateway.repository.fake.InMemoryVideoRepository;
import com.mytube.infra.security.context.fake.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mytube.core.application.command.UpdateVideoCommand;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateVideoCommandTest {
  InMemoryFileUploader fileUploader;
  InMemoryVideoRepository videoRepository;
  @BeforeEach
  void setup() {
    fileUploader = new InMemoryFileUploader();
    videoRepository = new InMemoryVideoRepository();
  }

  @Test
  void AnAdminUserUpdateVideoFromHisCollection() {

    var command = new UpdateVideoCommand(fileUploader, videoRepository);
    var request = new UpdateVideoRequest("1", InputStream.nullInputStream());
    var ctx = new TestContext(ClientRole.ADMIN);

    assertDoesNotThrow(()-> command.exec(request, ctx));
    assertTrue(fileUploader.toHaveBeenCallWith("replaceFile", "fakeVideoUrl"));
  }

  @Test
  void AnNonAdminUserTriesToUpdateVideoFromHisCollection() {
    var command = new UpdateVideoCommand(fileUploader, videoRepository);
    var request = new UpdateVideoRequest("1", InputStream.nullInputStream());
    var ctx = new TestContext(ClientRole.SUBSCRIBER);

    assertThrows(PermissionDeniedException.class, ()-> command.exec(request, ctx));
    assertFalse(fileUploader.toHaveBeenCall("replaceFile"));
  }
}
