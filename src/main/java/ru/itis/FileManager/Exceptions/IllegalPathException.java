package ru.itis.FileManager.Exceptions;

public class IllegalPathException extends RuntimeException{
  public IllegalPathException(String message) {
    super(message);
  }
}
