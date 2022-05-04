package com.musala.drone.repositories.mappers;


import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.repositories.models.DroneMO;
import com.musala.drone.repositories.models.LoadMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    @Mapping(source = "serial_number", target = "serial_number")
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

    default Optional<Drone> fromOptionalModel(Optional<DroneMO> optionalDroneMO) {

        return (optionalDroneMO.isEmpty()) ? Optional.empty() : Optional.of(fromModel(optionalDroneMO.get()));

    }

    default  Load loadMOToLoad(LoadMO loadMO) {
        if ( loadMO == null ) {
            return null;
        }

        Load.LoadBuilder load = Load.builder();

        load.id( loadMO.getId() );
        load.name( loadMO.getName() );
        load.code( loadMO.getCode() );
        load.weight( loadMO.getWeight() );
        load.imagePath( loadMO.getImagePath() );

        return load.build();
    }



}
