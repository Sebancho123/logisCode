package com.logistica.logisCode.service;

import com.logistica.logisCode.model.UserSec;
import com.logistica.logisCode.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService{

    @Autowired
    private IUserSecRepository iUserRepo;

    @Override
    public List<UserSec> findAll() {
        return iUserRepo.findAll();
    }

    @Override
    public UserSec save(UserSec userSec) {
        return iUserRepo.save(userSec);
    }

    @Override
    public String deleteById(Long id) {
        iUserRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return iUserRepo.findById(id);
    }

    @Override
    public UserSec update(UserSec userSec) {
        return this.save(userSec);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
