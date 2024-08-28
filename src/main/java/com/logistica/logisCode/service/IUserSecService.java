package com.logistica.logisCode.service;

import com.logistica.logisCode.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserSecService {

    public List<UserSec>findAll();

    public UserSec save(UserSec userSec);

    public String deleteById(Long id);

    public Optional<UserSec> findById(Long id);

    public UserSec update(UserSec userSec);

    public String encryptPassword(String password);

}
