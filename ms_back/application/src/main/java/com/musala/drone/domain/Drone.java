package com.musala.drone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Drone {

    private Long id;

    private String serial_number;

    private int weight;

    private ModelType model;

    private StateType state;

    private int battery;

    private Set<Load> loads;
}
