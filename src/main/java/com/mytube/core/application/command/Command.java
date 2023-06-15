package com.mytube.core.application.command;

import com.mytube.core.application.exceptions.PermissionDeniedException;
import com.mytube.core.port.driver.ClientContext;
import com.mytube.core.port.driver.ClientRole;

public abstract class Command<T> {

  protected static class AdminGuard {
    public static void against(ClientContext ctx) {
      if(!ctx.getAuthorities().equals(ClientRole.ADMIN))
        throw new PermissionDeniedException();
    }
  }

  public abstract void exec(T request, ClientContext ctx) throws Exception;
}
