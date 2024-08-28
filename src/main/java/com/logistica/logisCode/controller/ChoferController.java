package com.logistica.logisCode.controller;

import com.logistica.logisCode.model.Chofer;
import com.logistica.logisCode.service.IChoferService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chofer")
public class ChoferController {

    @Autowired
    private IChoferService iChoSer;

    @GetMapping("/getAll")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Chofer>> findAll(){
        List<Chofer> choferList = iChoSer.findAll();
        return ResponseEntity.ok(choferList);
    }

    @PostMapping("/save")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Chofer> save(@RequestBody Chofer chofer) {

        Chofer newChofer = iChoSer.save(chofer);
        return ResponseEntity.ok(newChofer);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Chofer> deleteById(@PathVariable Long id) {

        try {
            iChoSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Chofer> findById(@PathVariable Long id) {

        Optional<Chofer> chofer = iChoSer.findById(id);
        return chofer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Chofer> update(@RequestBody Chofer chofer) {

        Chofer newChofer = iChoSer.update(chofer);
        return ResponseEntity.ok(newChofer);
    }

}
