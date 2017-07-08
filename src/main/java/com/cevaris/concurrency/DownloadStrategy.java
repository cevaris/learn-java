package com.cevaris.concurrency;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface DownloadStrategy {
  ByteBuffer download(String url);
}

class MultiPartDownloadStrategy implements DownloadStrategy {
  @Override
  public ByteBuffer download(String url) {
    return ByteBuffer.allocate(0);
  }
}

class CachedDownloadStrategy implements DownloadStrategy {

  @Override
  public ByteBuffer download(String url) {
    return ByteBuffer.allocate(0);
  }
}

abstract class AbstractDownloader {
  private final DownloadStrategy downloadStrategy;
  private ExecutorService executor = Executors.newCachedThreadPool();

  AbstractDownloader(DownloadStrategy downloadStrategy) {
    this.downloadStrategy = downloadStrategy;
  }

  protected DownloadStrategy getDownloadStrategy() {
    return downloadStrategy;
  }

  abstract ByteBuffer download(URL url);
}

class PartialDownloader extends AbstractDownloader {


  PartialDownloader(DownloadStrategy downloadStrategy) {
    super(downloadStrategy);
  }

  @Override
  ByteBuffer download(URL url) {
    return null;
  }
}

class CompleteDownloader extends AbstractDownloader {
  CompleteDownloader(DownloadStrategy downloadStrategy) {
    super(downloadStrategy);
  }

  @Override
  ByteBuffer download(URL url) {
    return null;
  }
}

