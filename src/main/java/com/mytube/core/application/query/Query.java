package com.mytube.core.application.query;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.port.driver.ClientContext;
import com.mytube.core.port.driver.ClientRole;

public abstract class Query<T, R> {

  protected static class SubscriberGuard {
    public static void against(boolean isForSubscriber, ClientContext ctx) {
      if(isForSubscriber && ctx.getAuthorities().equals(ClientRole.FREE_VIEWER))
          throw new PermissionDeniedException();
    }
  }
  public abstract R getData(T arg, ClientContext ctx) throws Exception;
}
