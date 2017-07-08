package com.cevaris.util;


import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

interface TicketSystem {
  Long request(String name);

  Long status(Long ticket);

  String whoami(Long ticket);

  Long call();
}

class CounterTicketSystem implements TicketSystem {
  private final NavigableMap<Long, String> queue = new ConcurrentSkipListMap<>();
  private final Object lock = new Object();
  private final AtomicLong ticketGenerator = new AtomicLong(0L);

  @Override
  public Long request(String name) {
    synchronized (lock) {
      if (queue.containsValue(name)) {
        throw new IllegalArgumentException("name already taken: " + name);
      } else {
        Long ticket = ticketGenerator.getAndIncrement();
        queue.put(ticket, name);
        return ticket;
      }
    }
  }

  @Override
  public synchronized Long status(Long ticket) {
    if (!queue.containsKey(ticket)) {
      return null;
    }

    return (long) queue.subMap(queue.firstKey(), ticket).size();
  }

  @Override
  public String whoami(Long ticket) {
    return queue.get(ticket);
  }

  @Override
  public Long call() {
    Map.Entry<Long, String> next = queue.pollFirstEntry();
    if (next != null) {
      return next.getKey();
    } else {
      return null;
    }
  }
}




