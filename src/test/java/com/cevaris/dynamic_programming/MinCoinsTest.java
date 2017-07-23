package com.cevaris.dynamic_programming;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class MinCoinsTest {

  @Test(expected = IllegalArgumentException.class)
  public void testBadTarget() {
    Set<Integer> coins = new HashSet<>();
    coins.add(1);
    MinCoins.calculate(coins, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    MinCoins.calculate(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmpty() {
    MinCoins.calculate(new HashSet<>(), 1);
  }

  @Test
  public void testCase0() {
    Set<Integer> coins = new HashSet<>();
    coins.add(2);
    coins.add(3);
    coins.add(4);

    Assert.assertEquals(0, MinCoins.calculate(coins, 0));
    Assert.assertEquals(-1, MinCoins.calculate(coins, 1));
    Assert.assertEquals(1, MinCoins.calculate(coins, 2));
    Assert.assertEquals(1, MinCoins.calculate(coins, 3));
    Assert.assertEquals(1, MinCoins.calculate(coins, 4));
    Assert.assertEquals(2, MinCoins.calculate(coins, 5));
    Assert.assertEquals(2, MinCoins.calculate(coins, 6));
    Assert.assertEquals(2, MinCoins.calculate(coins, 7));
    Assert.assertEquals(2, MinCoins.calculate(coins, 8));
    Assert.assertEquals(3, MinCoins.calculate(coins, 9));
  }

  @Test
  public void testNotFound() {
    Set<Integer> coins = new HashSet<>();
    coins.add(10);
    coins.add(25);

    //Assert.assertEquals(-1, MinCoins.calculate(coins, 8));
    Assert.assertEquals(-1, MinCoins.calculate(coins, 22));
  }

}