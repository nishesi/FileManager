package ru.itis.FileManager.CommandElements.CommandModifiers;

public class CommandModifier {
  private String name;
  private String message;

  public CommandModifier(String name, String message) {
    this.name = name;
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public String getMessage() {
    return message;
  }
}
