package ru.itis.FileManager.CommandElements;

import ru.itis.FileManager.Exceptions.OperationFailedException;
import ru.itis.FileManager.Exceptions.ToFinishException;

public interface Executable {

  String execute(String[] args) throws OperationFailedException, ToFinishException;

}
