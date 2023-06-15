package core.application.query;

import com.mytube.core.application.query.VideoMetaDataPageQuery;
import com.mytube.core.port.driver.ClientRole;
import com.mytube.infra.gateway.repository.fake.InMemoryVideoQuery;
import com.mytube.infra.security.context.fake.TestContext;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;



public class MiniaturePageQueryTest {

  @Test
  void InitialRequestGetFirstPageOfVideoMiniature() {
    var query = new VideoMetaDataPageQuery(new InMemoryVideoQuery());
    var ctx = new TestContext(ClientRole.FREE_VIEWER);
    assertArrayEquals(List.of().toArray(), query.getData("1", ctx).toArray());
  }
}
