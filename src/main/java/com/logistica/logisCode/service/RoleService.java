package com.logistica.logisCode.service;

import com.logistica.logisCode.model.Role;
import com.logistica.logisCode.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository iRolRepo;


    @Override
    public List<Role> findAll() {
        return iRolRepo.findAll();
    }

    @Override
    public Role save(Role role) {
        return iRolRepo.save(role);
    }

    @Override
    public String deleteById(Long id) {
        iRolRepo.deleteById(id);
        return "delete successful";
    }

    @Override
    public Optional<Role> findById(Long id) {
        return iRolRepo.findById(id);
    }

    @Override
    public Role update(Role role) {
        return this.save(role);
    }
}
