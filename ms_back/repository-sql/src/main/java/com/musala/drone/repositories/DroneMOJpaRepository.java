package com.musala.drone.repositories;

import com.musala.drone.repositories.models.DroneMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneMOJpaRepository extends JpaRepository<DroneMO, Long> {
}
