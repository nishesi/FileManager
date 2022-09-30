package ru.itis.FileManager.CommandElements;

import ru.itis.FileManager.CommandElements.ExexutableCommands.*;

import java.util.HashMap;
import java.util.Map;

public class Commands {
  private static final Map<String, AbstractCommand> commandMap = new HashMap<>();

  public static Map<String, AbstractCommand> getCommands() {
    if (commandMap.isEmpty()) {
      AbstractCommand[] arr =
              {new LsCommand(),
                      new HelpCommand(),
                      new CdCommand(),
                      new CopyCommand(),
                      new ExitCommand()};

      for (AbstractCommand abstractCommand : arr) {
        commandMap.put(abstractCommand.getName(), abstractCommand);
      }
    }
    return commandMap;
  }

}
