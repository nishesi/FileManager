package ru.itis.FileManager.Main;

import ru.itis.FileManager.AuxiliaryClasses.CurrentDirectory;
import ru.itis.FileManager.CommandElements.AbstractCommand;
import ru.itis.FileManager.CommandElements.Commands;
import ru.itis.FileManager.CommandElements.Executable;
import ru.itis.FileManager.Exceptions.*;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

  private static boolean isStart = false;

  private static Scanner sc;

  private static Map<String, AbstractCommand> commandsMap;

  private static CurrentDirectory currentDirectory;

  public static Path getCurrentDirectory() {
    return currentDirectory.getCurrentDirectory();
  }

  public static void setCurrentDirectory(Path path) {
    currentDirectory.setCurrentDirectory(path);
  }


  public static void startMain(InputStream inputStream) {
    if (!isStart) {
      isStart = true;
      Main m = new Main();
      m.init(inputStream);
      m.start();
      m.end();
    }
  }


  private void init(InputStream inputStream) {
    sc = new Scanner(inputStream);
    commandsMap = Commands.getCommands();
    currentDirectory = new CurrentDirectory();
  }


  private void start() {
    String outputStr;

    while (true) {
      try {
        String[] args = getInput().split(" ", 2);

        Executable command = getEnteredCommand(args[0]);
        outputStr = command.execute(args);

        if (!outputStr.isEmpty()) {
          output(outputStr);
        }

      } catch (IllegalArgumentException ex) {
        output("something go wrong please try again");
      } catch (CommandNotSupportedException | OperationFailedException ex) {
        output("Err: " + ex.getMessage());
      } catch (ToFinishException ex) {
        break;
      }
    }
  }


  private void end() {
    System.out.println("File Manager finish work");
  }

  private String getInput() {
    System.out.println();
    output("Current directory: " + currentDirectory.getCurrentDirectory());
    output("Enter a command:");
    System.out.print("< ");
    if (sc.hasNextLine()) {
      return sc.nextLine();
    }
    throw new IllegalArgumentException("input error");
  }

  private Executable getEnteredCommand(String cmdName) throws CommandNotSupportedException{
    Set<Map.Entry<String, AbstractCommand>> mapEntry = commandsMap.entrySet();
    for (Map.Entry<String, AbstractCommand> entry : mapEntry) {
      if (cmdName.equals(entry.getKey())) {
        return entry.getValue();
      }
    }
    throw new CommandNotSupportedException("File manager Not support this command");
  }

  private void output(String str) {
    System.out.println("=> " + str);
  }

}
