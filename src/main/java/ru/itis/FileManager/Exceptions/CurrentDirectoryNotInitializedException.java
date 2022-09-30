package ru.itis.FileManager.Exceptions;

public class CurrentDirectoryNotInitializedException extends RuntimeException{

  public CurrentDirectoryNotInitializedException() {
  }

  public CurrentDirectoryNotInitializedException(String message) {
    super(message);
  }
}
