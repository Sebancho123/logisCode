package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Vehiculo;
import com.logistica.logisCode.repository.IVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService implements IVehiculoService{

    @Autowired
    private IVehiculoRepository iVehiRepo;

    @Override
    public List<Vehiculo> findAll() {
        return iVehiRepo.findAll();
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        return iVehiRepo.save(vehiculo);
    }

    @Override
    public String deleteById(Long id) {
        iVehiRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return iVehiRepo.findById(id);
    }

    @Override
    public Vehiculo update(Vehiculo vehiculo) {
        return this.save(vehiculo);
    }
}
