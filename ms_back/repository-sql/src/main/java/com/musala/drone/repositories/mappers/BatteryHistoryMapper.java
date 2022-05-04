package com.musala.drone.repositories.mappers;


import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.repositories.models.BatteryHistoryMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatteryHistoryMapper {

    BatteryHistory fromModel(BatteryHistoryMO batteryHistoryMO);

    BatteryHistoryMO toModel(BatteryHistory batteryHistory);


}
