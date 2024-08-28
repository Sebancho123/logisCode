package com.logistica.logisCode.controller;

import com.logistica.logisCode.model.Vehiculo;
import com.logistica.logisCode.service.IVehiculoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private IVehiculoService iVehiSer;

    @GetMapping("/getAll")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Vehiculo>> findAll(){

        List<Vehiculo> vehiculoList = iVehiSer.findAll();
        return ResponseEntity.ok(vehiculoList);

    }

    @PostMapping("/save")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehiculo> save(@RequestBody Vehiculo vehiculo) {
        Vehiculo newVehiculo = iVehiSer.save(vehiculo);
        return ResponseEntity.ok(newVehiculo);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehiculo> deleteById(@PathVariable Long id) {

        try {
            iVehiSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehiculo> findById(@PathVariable Long id) {

        Optional<Vehiculo> vehiculo = iVehiSer.findById(id);
        return vehiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Vehiculo> update(Vehiculo vehiculo) {
        Vehiculo newVehiculo = iVehiSer.update(vehiculo);
        return ResponseEntity.ok(newVehiculo);
    }

}
