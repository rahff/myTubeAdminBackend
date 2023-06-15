package com.mytube.infra.stub;

import java.lang.reflect.Method;
import java.util.*;

public class Stub<T> {

  protected Map<String, List<Object>> methodCalls = new HashMap<>();
  public Stub(Class<T> mocked){
    var methods = mocked.getDeclaredMethods();
    for (Method method: methods) {
      methodCalls.put(method.getName(), new ArrayList<>());
    }
  }
  public <X> boolean toHaveBeenCallWith(String methodName, X arg) {
    var foundedMethodCall = methodCalls.getOrDefault(methodName, new ArrayList<>());
    return foundedMethodCall.contains(arg);
  }

  public boolean toHaveBeenCall(String methodName) {
    var foundedMethodCall = methodCalls.getOrDefault(methodName, null);
    return foundedMethodCall.size() > 0;
  }

  protected void registerMethodCall(String methodName, Object... args) {
    Arrays.stream(args).sequential().forEach((a) -> methodCalls.get(methodName).add(a));
  }
}
