package ru.itis.FileManager.Exceptions;

public class CommandNotSupportedException extends Exception {
  public CommandNotSupportedException(String message) {
    super(message);
  }
}
