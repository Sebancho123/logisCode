package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Viaje;

import java.util.List;
import java.util.Optional;

public interface IViajeService {

    public List<Viaje> findAll();

    public Viaje save(Viaje viaje);

    public String deleteById(Long id);

    public Optional<Viaje> findById(Long id);

    public Viaje update(Viaje viaje);

}
