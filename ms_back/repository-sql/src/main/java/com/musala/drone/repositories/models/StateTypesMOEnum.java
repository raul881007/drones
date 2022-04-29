package com.musala.drone.repositories.models;

import lombok.Getter;

@Getter
public enum StateTypesMOEnum {

  IDLE("IDLE"),
  LOADING("LOADING"),
  LOADED("LOADED"),
  DELIVERING("DELIVERING"),
  DELIVERED("DELIVERED"),
  RETURNING("RETURNING");

  private final String value;
  StateTypesMOEnum(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

}
