package ru.itis.FileManager.CommandElements.ExexutableCommands;

import ru.itis.FileManager.CommandElements.AbstractCommand;
import ru.itis.FileManager.CommandElements.CommandModifiers.CommandModifier;
import ru.itis.FileManager.Exceptions.IllegalPathException;
import ru.itis.FileManager.Exceptions.OperationFailedException;
import ru.itis.FileManager.Main.Main;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand extends AbstractCommand {

  public CdCommand() {
    super("cd", "goes to the specified directory");
    modifiers = new CommandModifier[0];
  }


  @Override
  public String execute(String[] args) throws OperationFailedException {
    if (args.length <= 1) {
      throw new OperationFailedException("Not enough arguments to execute a command");
    }
    Path path = Main.getCurrentDirectory().resolve(Paths.get(args[1])).normalize();
    if (path.toFile().isFile()) {
      throw new OperationFailedException("specified path is not a directory");
    }
    try {
      Main.setCurrentDirectory(path);
    } catch (IllegalPathException ex) {
      throw new OperationFailedException("unreachable path");
    }
    return "";
  }
}

