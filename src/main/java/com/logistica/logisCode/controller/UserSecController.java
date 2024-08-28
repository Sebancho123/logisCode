package com.logistica.logisCode.controller;

import com.logistica.logisCode.model.Role;
import com.logistica.logisCode.model.UserSec;
import com.logistica.logisCode.service.IRoleService;
import com.logistica.logisCode.service.IUserSecService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserSecController {

    @Autowired
    private IUserSecService iUserSer;

    @Autowired
    private IRoleService iRolSer;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserSec>> getAll(){

        List<UserSec> userSecList = iUserSer.findAll();
        return ResponseEntity.ok(userSecList);

    }

    @PostMapping("/save")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> save(@RequestBody UserSec userSec) {

        Set<Role> rolesList = new HashSet<>();

        //encriptamos contra
        userSec.setPassword(iUserSer.encryptPassword(userSec.getPassword()));

        for (Role role : userSec.getRolesList()) {

            Role readRole = iRolSer.findById(role.getId()).orElse(null);
            if(readRole != null) {
                rolesList.add(role);
            }
        }

        if(!rolesList.isEmpty()) {
            userSec.setRolesList(rolesList);
            UserSec newUserSec = iUserSer.save(userSec);
            return ResponseEntity.ok(newUserSec);
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> deleteById(@PathVariable Long id) {

        try{
            iUserSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserSec> findById(@PathVariable Long id) {

        Optional<UserSec> userSec = iUserSer.findById(id);
        return userSec.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/update")
    public ResponseEntity<UserSec> update(@RequestBody UserSec userSec) {

        //encriptamos contra
        userSec.setPassword(iUserSer.encryptPassword(userSec.getPassword()));

        UserSec newUserSec = iUserSer.update(userSec);
        return ResponseEntity.ok(newUserSec);
    }

}
