package com.mytube.infra.security.context.fake;

import com.mytube.core.port.driver.ClientContext;
import com.mytube.core.port.driver.ClientRole;

public class TestContext implements ClientContext {

  private final ClientRole clientRole;
  public TestContext(ClientRole clientRole){
    this.clientRole = clientRole;
  }
  public ClientRole getAuthorities() {
    return clientRole;
  }
}
