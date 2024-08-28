package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Viaje;
import com.logistica.logisCode.repository.IViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeService implements IViajeService{

    @Autowired
    private IViajeRepository iViaRepo;


    @Override
    public List<Viaje> findAll() {
        return iViaRepo.findAll();
    }

    @Override
    public Viaje save(Viaje viaje) {
        return iViaRepo.save(viaje);
    }

    @Override
    public String deleteById(Long id) {
        iViaRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<Viaje> findById(Long id) {
        return iViaRepo.findById(id);
    }

    @Override
    public Viaje update(Viaje viaje) {
        return this.save(viaje);
    }
}
