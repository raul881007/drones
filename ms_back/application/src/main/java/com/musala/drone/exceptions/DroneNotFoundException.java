package com.musala.drone.exceptions;

import com.musala.drone.domain.Drone;

public class DroneNotFoundException extends Exception {

    public DroneNotFoundException(String message) {

        super(message);

    }
}
