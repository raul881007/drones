package com.musala.drone.repositories.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drone")
public class DroneMO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", length = 100)
    private String serial_number;

    @Column(name = "weight")
    @Max(value = 500, message = "Weight must be up 500")
    private int weight;

    @Column(name = "battery")
    @Max(value = 100, message = "Battery level must be up 100")
    private int battery;


    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private ModelTypesMOEnum model;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateTypesMOEnum state;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY,cascade= CascadeType.REMOVE)
    private Set<LoadMO> loads;

}
