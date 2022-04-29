package com.musala.drone.repositories.mappers;


import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.repositories.models.DroneMO;
import com.musala.drone.repositories.models.LoadMO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LoadMapper {

    Load fromModel(LoadMO loadMO);

    LoadMO toModel(Load load);


    default Iterable<Load> fromModels(Iterable<LoadMO> droneModelPage) {
        //Creates a list
        List<LoadMO> itList = new ArrayList<LoadMO>();
        //Adding iterable elements to list
        droneModelPage.forEach(itList::add);
        //Converting elements and returning it
        return itList.stream().map(this::fromModel).collect(Collectors.toList());

    }
}
