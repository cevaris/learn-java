package com.cevaris.test.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.junit.After;
import org.junit.Before;

public abstract class RmiTest<A> {

  private Registry registry;
  private int registryPort;

  private A server;
  protected A client;

  abstract protected String name();

  abstract protected A createServer();

  @Before
  public void beforeEach() {

    try {
      server = createServer();
      Remote stub = UnicastRemoteObject.exportObject((Remote) server, 0);

      registryPort = nextPort();
      registry = LocateRegistry.createRegistry(registryPort);
      registry.bind(name(), stub);

      client = (A) registry.lookup(name());
    } catch (Exception e) {
      System.err.println("Server exception: " + e.toString());
      e.printStackTrace();
    }
  }


  @After
  public void afterEach() {
    try {
      if (server != null) {
        UnicastRemoteObject.unexportObject((Remote) server, false);
      }
      if (registry != null) {
        registry.unbind(name());
      }
    } catch (Exception e) {
      System.err.println("Server shutdown exception: " + e.toString());
      e.printStackTrace();
    }
  }

  private int nextPort() throws IOException {
    ServerSocket socket = null;
    try {
      socket = new ServerSocket(0);
      return socket.getLocalPort();
    } catch (IOException e) {
      return nextPort();
    } finally {
      if (socket != null && !socket.isClosed()) {
        socket.close();
      }
    }
  }


}
