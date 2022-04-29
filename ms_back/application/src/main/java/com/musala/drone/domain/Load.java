package com.musala.drone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Load {

    private Long id;

    private String name;

    private String code;

    private int weight;

    private String imagePath;

    private Drone drone;
}
