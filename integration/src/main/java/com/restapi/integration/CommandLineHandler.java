package com.restapi.integration;

public interface CommandLineHandler<T> {
  public void handle(T cmd);
}
