package com.musala.drone.repositories.mappers;


import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.repositories.models.DroneMO;
import com.musala.drone.repositories.models.LoadMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LoadMapper {

    LoadMO toModel(Load load);


    default Iterable<Load> fromModels(Iterable<LoadMO> droneModelPage) {
        //Creates a list
        List<LoadMO> itList = new ArrayList<LoadMO>();
        //Adding iterable elements to list
        droneModelPage.forEach(itList::add);
        //Converting elements and returning it
        return itList.stream().map(this::fromModel).collect(Collectors.toList());

    }

    default Optional<Load> fromOptionalModel(Optional<LoadMO> optionalLoadMO) {

        return (optionalLoadMO.isEmpty()) ? Optional.empty() : Optional.of(fromModel(optionalLoadMO.get()));

    }

    default  Load fromModel(LoadMO loadMO) {
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
