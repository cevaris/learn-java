package com.cevaris.concurrency;


import java.rmi.Remote;
import java.rmi.RemoteException;

interface StatelessServerIface extends Remote {
  Integer doubler(Integer x) throws RemoteException;
}

public class StatelessServer implements StatelessServerIface {
  @Override
  public Integer doubler(Integer x) throws RemoteException {
    return 2 * x;
  }
}
