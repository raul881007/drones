package com.musala.drone.repositories.mappers;


import com.musala.drone.domain.Drone;
import com.musala.drone.repositories.models.DroneMO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    Drone fromModel(DroneMO droneMO);

    DroneMO toModel(Drone drone);

    default Iterable<Drone> fromModels(Iterable<DroneMO> droneModelPage) {
        //Creates a list
        List<DroneMO> itList = new ArrayList<DroneMO>();
        //Adding iterable elements to list
        droneModelPage.forEach(itList::add);
        //Converting elements and returning it
        return itList.stream().map(this::fromModel).collect(Collectors.toList());

    }



}
