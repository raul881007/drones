package com.musala.drone.domain;

import lombok.Getter;

@Getter
public enum StateType {

    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private final String value;
    StateType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
