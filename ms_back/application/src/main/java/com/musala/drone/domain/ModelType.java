package com.musala.drone.domain;

import lombok.Getter;

@Getter
public enum ModelType {

    Lightweight("Lightweight"),
    Middleweight("Middleweight"),
    Cruiserweight("Cruiserweight"),
    Heavyweight("Heavyweight");

    private final String value;

    ModelType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
