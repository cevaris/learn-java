package com.cevaris.patterns.adaptor;


import java.io.File;
import java.io.IOException;

/**
 * HdfsFile is uses the Adaptor pattern to act like a java.io.File.
 */
public class HdfsFile extends File {
  private final FileSystem fs = FileSystem.get();
  private final Path path;

  public HdfsFile(String pathname) {
    super("");
    this.path = new Path(pathname);
  }

  @Override
  public boolean delete() {
    return fs.delete(path, false);
  }

  @Override
  public void deleteOnExit() {
    Runnable r = new Runnable() {
      final Path p = path;

      @Override
      public void run() {
        HdfsFile f = new HdfsFile(p.getPath());
        if (f.exists()) f.delete();
      }
    };
    Runtime.getRuntime().addShutdownHook(new Thread(r));
  }

  @Override
  public boolean exists() {
    return fs.exists(path);
  }

  @Override
  public boolean mkdirs() {
    return fs.mkdirs(path);
  }

  @Override
  public boolean createNewFile() throws IOException {
    FSDataOutputStream result = fs.create(this.path);
    return result != null;
  }
}
