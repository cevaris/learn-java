package com.cevaris.util;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.WeakHashMap;

interface RainbowTable {

  void add(String string);

  int size();

}

class WeakRainbowTable implements RainbowTable {
  private final Map<String, ByteBuffer> store;

  public WeakRainbowTable() {
    store = new WeakHashMap<>();
  }

  public void add(String string) {
    MessageDigest digest = getMd5();
    digest.digest(string.getBytes());
    byte[] hash = digest.digest();
    store.put(string, ByteBuffer.wrap(hash));
  }

  private MessageDigest getMd5() {
    try {
      return MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public int size() {
    return store.size();
  }
}
