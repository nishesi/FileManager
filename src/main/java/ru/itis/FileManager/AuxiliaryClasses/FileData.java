package ru.itis.FileManager.AuxiliaryClasses;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class FileData {
  protected File file;
  protected String name;
  protected Date date;
  protected long size = -1;
  protected String[] marks;

  public FileData(File file) {
    this.file = file;
    name = file.getName();
    date = new Date(file.lastModified());
  }

  public String getName() {
    return name;
  }

  public long getSize() {
    if (size == -1) {
      size = calcSize(file);
    }
    return size;
  }

  public Date getFileDate() {
    return date;
  }
  public String getFileMarks() {
    if (marks == null) {
      marks = new String[3];
      if (file.canExecute()) {
        marks[0] = "-exe";
      } else {
        marks[0] = "    ";
      }
      if (file.canRead()) {
        marks[1] = "-r";
      } else {
        marks[1] = "  ";
      }
      if (file.canWrite()) {
        marks[2] = "-w";
      } else {
        marks[2] = "  ";
      }
    }
    return marks[0] + marks[1] + marks[2];
  }

  public static long calcSize(File file) {
    if (file.isFile()) {
      try {
        return file.length();
      } catch (SecurityException ex) {
        return 0;
      }
    }

    File[] files = file.listFiles();
    if (files == null) {
      return 0;
    }
    return Arrays.stream(files).mapToLong(FileData::calcSize).sum();
  }
}
