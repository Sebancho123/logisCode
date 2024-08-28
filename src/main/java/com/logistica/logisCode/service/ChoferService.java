package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Chofer;
import com.logistica.logisCode.repository.IChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoferService implements IChoferService{

    @Autowired
    private IChoferRepository iChoRepo;

    @Override
    public List<Chofer> findAll() {
        return iChoRepo.findAll();
    }

    @Override
    public Chofer save(Chofer chofer) {
        return iChoRepo.save(chofer);
    }

    @Override
    public String deleteById(Long id) {
        iChoRepo.deleteById(id);
        return "delete Success";
    }

    @Override
    public Optional<Chofer> findById(Long id) {
        return iChoRepo.findById(id);
    }

    @Override
    public Chofer update(Chofer chofer) {
        return this.save(chofer);
    }
}
