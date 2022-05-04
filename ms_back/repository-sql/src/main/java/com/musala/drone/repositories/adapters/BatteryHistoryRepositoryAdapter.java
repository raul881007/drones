package com.musala.drone.repositories.adapters;

import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.domain.Drone;
import com.musala.drone.ports.out.BatteryHistoryRepositoryPort;
import com.musala.drone.repositories.BatteryHistoryMOJpaRepository;
import com.musala.drone.repositories.mappers.BatteryHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class BatteryHistoryRepositoryAdapter implements BatteryHistoryRepositoryPort {


    private final BatteryHistoryMOJpaRepository repository;

    private final BatteryHistoryMapper mapper;

    public BatteryHistoryRepositoryAdapter(BatteryHistoryMOJpaRepository repository, BatteryHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Save an element in Database
     *
     * @param batteryHistory element to save in database
     * @return BatteryHistory object
     * @author Raul Herrera
     */
    @Override
    public BatteryHistory save(BatteryHistory batteryHistory) {

        var batteryHistoryMO = mapper.toModel(batteryHistory);

        var batteryHistorySaved = repository.save(batteryHistoryMO);

        return mapper.fromModel(batteryHistoryMO);

    }

    @Override public <S extends BatteryHistory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override public Optional<BatteryHistory> findById(Long aLong) {
        return Optional.empty();
    }

    @Override public boolean existsById(Long aLong) {
        return false;
    }

    @Override public Iterable<BatteryHistory> findAll() {
        return null;
    }

    @Override public Iterable<BatteryHistory> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override public long count() {
        return 0;
    }

    @Override public void deleteById(Long aLong) {

    }

    @Override public void delete(BatteryHistory entity) {

    }

    @Override public void deleteAll(Iterable<? extends BatteryHistory> entities) {

    }

    @Override public void deleteAll() {

    }
}
