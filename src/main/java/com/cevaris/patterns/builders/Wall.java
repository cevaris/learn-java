package com.cevaris.patterns.builders;


import java.util.Objects;

class Wall implements MapSite {
  private Object object;

  @Override
  public void enter() {
  }

  @Override
  public int hashCode() {
    return Objects.hash(object);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Wall other = (Wall) obj;
    return Objects.equals(this.object, other.object);
  }
}
