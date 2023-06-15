package core.application.query;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.application.query.VideoStreamUrlQuery;
import com.mytube.core.port.driver.ClientRole;
import com.mytube.infra.gateway.provider.cloudResource.fake.InMemoryStreamUrlProvider;
import com.mytube.infra.gateway.repository.fake.InMemoryVideoQuery;
import com.mytube.infra.security.context.fake.TestContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VideoStreamUrlQueryTest {

  @Test
  void AFreeViewerRequestForWatchingAVideo() throws Exception {
    var streamUrlProvider = new InMemoryStreamUrlProvider();
    var videoMetaDataQuery = new InMemoryVideoQuery();
    var query = new VideoStreamUrlQuery(streamUrlProvider, videoMetaDataQuery);
    var ctx = new TestContext(ClientRole.FREE_VIEWER);
    var signedUrl = query.getData("resourceIdentifier", ctx);
    assertEquals("resourceIdentifier/fakeSignedUrl", signedUrl);
  }

  @Test
  void AFreeViewerRequestForWatchingAPrivateSubscriberVideo() {
    var streamUrlProvider = new InMemoryStreamUrlProvider();
    var videoMetaDataQuery = new InMemoryVideoQuery();
    var query = new VideoStreamUrlQuery(streamUrlProvider, videoMetaDataQuery);
    var ctx = new TestContext(ClientRole.FREE_VIEWER);
    assertThrows(PermissionDeniedException.class,
      ()-> query.getData("privateVideoIdentifier", ctx));

  }

  @Test
  void ASubscriberViewerRequestForWatchingAPrivateSubscriberVideo() throws Exception {
    var streamUrlProvider = new InMemoryStreamUrlProvider();
    var videoMetaDataQuery = new InMemoryVideoQuery();
    var query = new VideoStreamUrlQuery(streamUrlProvider, videoMetaDataQuery);
    var ctx = new TestContext(ClientRole.SUBSCRIBER);
    var signedUrl = query.getData("privateVideoIdentifier", ctx);
    assertEquals("privateVideoIdentifier/fakeSignedUrl", signedUrl);

  }
}
