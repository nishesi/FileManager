package ru.itis.FileManager.CommandElements.ExexutableCommands;

import ru.itis.FileManager.CommandElements.AbstractCommand;
import ru.itis.FileManager.Exceptions.OperationFailedException;
import ru.itis.FileManager.Main.Main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyCommand extends AbstractCommand {

  public CopyCommand() {
    super("copy", "copy file to your path");
  }

  @Override
  public String execute(String[] args) throws OperationFailedException {
    if (args.length < 3) {
      throw new OperationFailedException("not enough arguments to execute copy command");
    } else {
      Path path = Paths.get(args[1]);
      if (path.isAbsolute()) {
        copy(path, Paths.get(args[2]));
      } else {
        path = Main.getCurrentDirectory().resolve(Paths.get(args[1]));
        copy(path, Paths.get(args[2]));
      }
    }
    return "";
  }

  private void copy(Path filesPath, Path newPath) throws OperationFailedException {
    if (!newPath.toFile().isDirectory()) {
      throw new OperationFailedException("not correct directory to copy");
    }

    File copyFile = newPath.resolve(filesPath.getFileName()).toFile();
    try {
      if (!copyFile.createNewFile()) {
        throw new OperationFailedException("file exists in specified directory");
      }
    } catch (IOException ex) {
      throw new OperationFailedException(ex.getMessage());
    }

    try (FileInputStream input = new FileInputStream(filesPath.toFile());
         FileOutputStream output = new FileOutputStream(copyFile);) {

      while (input.available() > 0) {
        output.write(input.read());
      }

    } catch (FileNotFoundException ex) {
      throw new OperationFailedException("file not found");

    } catch (IOException e) {
      throw new OperationFailedException("file not readable");
    }
  }
}
