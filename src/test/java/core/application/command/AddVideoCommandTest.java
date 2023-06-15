package core.application.command;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.port.driver.ClientRole;
import com.mytube.core.port.driver.CreateVideoRequest;
import com.mytube.core.port.dto.VideoMiniatureDto;
import com.mytube.infra.gateway.provider.fileStorage.fake.InMemoryFileUploader;
import com.mytube.infra.gateway.repository.fake.InMemoryVideoRepository;
import com.mytube.infra.security.context.fake.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mytube.core.application.command.AddVideoCommand;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;



public class AddVideoCommandTest {

  InMemoryVideoRepository repository;
  InMemoryFileUploader fileUploader;

  @BeforeEach
  void setup() {
    repository = new InMemoryVideoRepository();
    fileUploader = new InMemoryFileUploader();
  }

  @Test
  void AnAdminUserAddNewVideoOnCollection() {
    var request = new CreateVideoRequest(FileInputStream.nullInputStream(), FileInputStream.nullInputStream(), "fakeDescription", "title", true);
    var ctx = new TestContext(ClientRole.ADMIN);
    var command = new AddVideoCommand(repository, fileUploader);
    var expectedResult = new VideoMiniatureDto(null, "fakeUrl", "fakeUrl", "fakeDescription", "title", true);

    assertDoesNotThrow(()-> command.exec(request, ctx));
    assertTrue(repository.toHaveBeenCallWith("addVideoMetaData", expectedResult));
    assertTrue(fileUploader.toHaveBeenCallWith("uploadFile", "file"));
  }

  @Test
  void AnNonAdminUserTriesToAddNewVideoOnCollection() {
    var request = new CreateVideoRequest(FileInputStream.nullInputStream(), FileInputStream.nullInputStream(), "fakeDescription", "title", true);
    var ctx = new TestContext(ClientRole.SUBSCRIBER);
    var command = new AddVideoCommand(repository, fileUploader);

    assertThrows(PermissionDeniedException.class, ()-> command.exec(request, ctx));
    assertFalse(repository.toHaveBeenCall("addVideoMetaData"));
    assertFalse(fileUploader.toHaveBeenCall("uploadFile"));
  }
}
