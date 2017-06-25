package com.cevaris.concurrency;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

interface CachedFactorServerIface extends Remote {
  BigInteger[] calcFactors(BigInteger x) throws RemoteException;
}

public class CachedFactorServer implements CachedFactorServerIface {

  private Long cacheHitCount = 0L;
  private Long requestCount = 0L;

  private BigInteger[] lastFactors;
  private BigInteger lastNumber;

  @Override
  public BigInteger[] calcFactors(BigInteger x) throws RemoteException {

    synchronized (this) {
      requestCount++;
      if (lastNumber != null && lastNumber.equals(x)) {
        cacheHitCount++;
        return lastFactors;
      }
    }

    // execute expensive factor
    BigInteger[] newFactors = {BigInteger.valueOf(x.hashCode())};

    synchronized (this) {
      lastFactors = newFactors.clone();
      lastNumber = x;
    }

    return newFactors;
  }

  public Long getRequestCount() {
    return requestCount;
  }
}
