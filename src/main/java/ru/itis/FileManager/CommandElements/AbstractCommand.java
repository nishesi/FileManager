package ru.itis.FileManager.CommandElements;

import ru.itis.FileManager.CommandElements.CommandModifiers.CommandModifier;
import ru.itis.FileManager.Exceptions.OperationFailedException;
import ru.itis.FileManager.Exceptions.ToFinishException;

import java.util.NoSuchElementException;

public abstract class AbstractCommand implements Executable {
  protected String name;
  protected String message;
  protected CommandModifier[] modifiers;

  public AbstractCommand(String name, String message) {
    this.name = name;
    this.message = message;;
  }

  public String getName() {
    return name;
  }

  public String message() {
    return message;
  }

  public boolean containsModifier(String modifierName) {
    for (CommandModifier modifier : modifiers) {
      if (modifier.getName().equals(modifierName)) {
        return true;
      }
    }
    return false;
  }

  public CommandModifier getCmdModifier(String name) {
    for (CommandModifier modifier : modifiers) {
      if (modifier.getName().equals(name)) {
        return modifier;
      }
    }
    throw new NoSuchElementException("No such modifier");
  }

  @Override
  public abstract String execute(String[] args) throws OperationFailedException, ToFinishException;
}
