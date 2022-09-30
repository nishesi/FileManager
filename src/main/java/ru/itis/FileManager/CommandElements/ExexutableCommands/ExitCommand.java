package ru.itis.FileManager.CommandElements.ExexutableCommands;

import ru.itis.FileManager.CommandElements.AbstractCommand;
import ru.itis.FileManager.Exceptions.ToFinishException;

public class ExitCommand extends AbstractCommand {

  public ExitCommand() {
    super("exit", "finishes file managers work");
  }

  @Override
  public String execute(String[] args) throws ToFinishException {
    throw new ToFinishException("end");
  }
}
