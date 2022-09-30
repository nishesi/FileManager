package ru.itis.FileManager.CommandElements.ExexutableCommands;

import ru.itis.FileManager.CommandElements.AbstractCommand;
import ru.itis.FileManager.CommandElements.Commands;
import ru.itis.FileManager.Exceptions.OperationFailedException;

import java.util.Map;

public class HelpCommand extends AbstractCommand {

  public HelpCommand() {
    super("help", "output all commands and their purpose");
  }

  @Override
  public String execute(String[] args) throws OperationFailedException {
    Map<String, AbstractCommand> map = Commands.getCommands();
    StringBuilder str = new StringBuilder();

    for (Map.Entry<String, AbstractCommand> entry : map.entrySet()) {
      AbstractCommand cmd = entry.getValue();
      str.append("\t" + cmd.getName() + "  \t:\t" + cmd.message() + "\n");
    }
    return str.toString();
  }
}

