package com.logistica.logisCode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viajes")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lugar;
    private String fecha;
    private double cantKlmt;

    @OneToOne
    @JoinColumn(name = "chofer_id")
    private Chofer unChofer;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo unVehiculo;

}
