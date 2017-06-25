package com.cevaris.concurrency;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicLong;

interface ServerWithHitCounterIface extends Remote {
  void func() throws RemoteException;
}

public class ServerWithHitCounter implements ServerWithHitCounterIface {
  private AtomicLong hitCount = new AtomicLong(0);

  @Override
  public void func() throws RemoteException {
    hitCount.incrementAndGet();
  }

  public Long getHitCount() {
    return hitCount.get();
  }

}
