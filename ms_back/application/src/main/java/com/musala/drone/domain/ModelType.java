package com.musala.drone.domain;

import lombok.Getter;

@Getter
public enum ModelType {

    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISERWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    private final String value;

    ModelType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
