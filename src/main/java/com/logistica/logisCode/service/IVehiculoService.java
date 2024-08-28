package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Chofer;
import com.logistica.logisCode.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {

    public List<Vehiculo> findAll();

    public Vehiculo save(Vehiculo vehiculo);

    public String deleteById(Long id);

    public Optional<Vehiculo> findById(Long id);

    public Vehiculo update(Vehiculo vehiculo);

}
