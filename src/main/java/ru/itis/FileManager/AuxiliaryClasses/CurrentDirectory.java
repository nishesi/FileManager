package ru.itis.FileManager.AuxiliaryClasses;

import ru.itis.FileManager.Exceptions.CurrentDirectoryNotInitializedException;
import ru.itis.FileManager.Exceptions.IllegalPathException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CurrentDirectory {
  private Path currentDirectory;

  public CurrentDirectory() {
    currentDirectory = Paths.get("C://");
  }

  public CurrentDirectory(Path path) {
    setCurrentDirectory(path);
  }

  public void setCurrentDirectory(Path path) {
    if (path.toFile().isDirectory()) {
      currentDirectory = path;
    } else {
      throw new IllegalPathException("path is not hidden");
    }
  }

  public Path getCurrentDirectory() {
    if (currentDirectory != null) {
      return currentDirectory;
    }
    throw new CurrentDirectoryNotInitializedException();
  }

}
