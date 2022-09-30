package ru.itis.FileManager.CommandElements.ExexutableCommands;

import ru.itis.FileManager.AuxiliaryClasses.FileData;
import ru.itis.FileManager.CommandElements.AbstractCommand;
import ru.itis.FileManager.CommandElements.CommandModifiers.CommandModifier;
import ru.itis.FileManager.Exceptions.OperationFailedException;
import ru.itis.FileManager.Main.Main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class LsCommand extends AbstractCommand {

  public LsCommand() {
    super("ls", "input all files on current directory");
    modifiers = new CommandModifier[]{
            new CommandModifier("inf", "output information about files or directories"),
            new CommandModifier("rdl", "outputs readable information about files or directories")};
  }

  @Override
  public String execute(String[] args) throws OperationFailedException {
    if (args.length > 1 && !containsModifier(args[1])) {
      throw new OperationFailedException("unknown modifier");
    }

    File[] files = Main.getCurrentDirectory().toFile().listFiles();
    if (files == null) {
      return "directory is void";
    }

    FileData[] filesArr = initFilesArr(files);

    int maxNameLen = Arrays.stream(filesArr).map(x -> x.getName().length()).max(Comparator.comparingInt(x -> x)).get();

    if (args.length <= 1) {
      return getFileNames(filesArr, maxNameLen);
    } else if (args[1].equals("inf")){
      return getInf(filesArr, maxNameLen);
    }
    return getReadableInf(filesArr, maxNameLen);
  }

  private String getFileNames(FileData[] directoryFiles, int maxNameLen) {

    StringBuilder str = new StringBuilder();
    int i = 0;

    for (FileData file : directoryFiles) {
      String fileName = file.getName();

      str.append("\t").append(fileName).append(getSpaces(fileName.length(), maxNameLen));
      if (i % 2 == 1) {
        str.append("\n");
      }
      i++;
    }
    return str.toString();
  }

  private String getInf(FileData[] directoryFiles, int maxNameLen) {
    StringBuilder str = new StringBuilder();

    long[] filesSizes = Arrays.stream(directoryFiles).mapToLong(FileData::getSize).toArray();

    int maxSizeLen = String.valueOf(
            Arrays.stream(filesSizes).max().getAsLong()
                                    ).length();

    for (int i = 0; i < directoryFiles.length; i++) {
      FileData file = directoryFiles[i];
      str.append("\t| ").append(file.getFileMarks());
      str.append("\t| ").append(file.getName()).   append(getSpaces(file.getName().length(), maxNameLen));
      str.append("\t| ").append(filesSizes[i]).    append(getSpaces(String.valueOf(filesSizes[i]).length(), maxSizeLen));
      str.append("\t| ").append(getFileDate(file)).append("\n");

    }
    return str.toString();
  }

  private String getReadableInf(FileData[] directoryFiles, int maxNameLen) {
    StringBuilder str = new StringBuilder();

    for (FileData file : directoryFiles) {
      str.append("\t marks: " + file.getFileMarks());
      str.append("\t Name: " + file.getName() + getSpaces(file.getName().length(), maxNameLen));
      str.append("\t Size: " + getReadableSize((double)file.getSize()));
      str.append("\t Date: " + getFileDate(file) + "\n");
    }
    return str.toString();
  }

  private String getReadableSize(double size) {
    String[] unitOfMeasurement = {"B", "KB", "MB", "GB", "TB"};

    int i = 0;
    while (size > 100 && i < unitOfMeasurement.length-1) {
      size = size / 1024;
      i++;
    }
    size = (double)Math.round(size*100)/100;
    return size + " " + unitOfMeasurement[i];
  }

  private String getSpaces(int nameLen, int maxLen) {
    String str = "";
    for (int i = 0; i <maxLen - nameLen; i++) {
      str += " ";
    }
    return str;
  }

  private String getFileDate(FileData file) {
    return new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(file.getFileDate());
  }

  private FileData[] initFilesArr(File[] files) {

    FileData[] filesArr = Arrays.stream(files)
            .map(FileData::new)
            .toArray(value -> new FileData[files.length]);
    Arrays.sort(filesArr, (x1, x2) -> x2.getName().length() - x1.getName().length());

    Arrays.stream(filesArr).mapToLong(FileData::getSize);

    Arrays.stream(filesArr).map(FileData::getFileMarks);

    return filesArr;
  }
}

