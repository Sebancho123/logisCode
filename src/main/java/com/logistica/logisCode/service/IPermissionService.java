package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    public List<Permission> findAll();

    public Permission save(Permission permission);

    public String deleteById(Long id);

    public Optional<Permission> findById(Long id);

    public Permission update(Permission permission);

}
