package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Chofer;

import java.util.List;
import java.util.Optional;

public interface IChoferService {

    public List<Chofer> findAll();

    public Chofer save(Chofer chofer);

    public String deleteById(Long id);

    public Optional<Chofer> findById(Long id);

    public Chofer update(Chofer chofer);
}
