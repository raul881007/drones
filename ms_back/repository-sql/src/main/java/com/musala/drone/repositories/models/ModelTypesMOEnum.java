package com.musala.drone.repositories.models;

import lombok.Getter;

@Getter
public enum ModelTypesMOEnum {

  LIGHTWEIGHT("Lightweight"),
  MIDDLEWEIGHT("Middleweight"),
  CRUISERWEIGHT("Cruiserweight"),
  HEAVYWEIGHT("Heavyweight");

  private final String value;

  ModelTypesMOEnum(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

}
