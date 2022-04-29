package com.musala.drone.repositories;

import com.musala.drone.repositories.models.LoadMO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadMOJpaRepository extends JpaRepository<LoadMO, Long> {
    @EntityGraph(attributePaths = { "drone" })
    List<LoadMO> findAllByDrone_Id(Long id);
}
