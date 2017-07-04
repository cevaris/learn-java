package com.cevaris.patterns.adaptor;


import java.io.IOException;

class FileSystem {
  public static FileSystem get() {
    return new FileSystem();
  }

  boolean exists(Path path) {
    return true;
  }

  boolean delete(Path path, boolean recursive) {
    return true;
  }

  FSDataOutputStream create(Path path) throws IOException {
    return new FSDataOutputStream();
  }

  boolean mkdirs(Path path) {
    return true;
  }
}
