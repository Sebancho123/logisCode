package com.logistica.logisCode.controller;

import com.logistica.logisCode.model.Chofer;
import com.logistica.logisCode.model.Vehiculo;
import com.logistica.logisCode.model.Viaje;
import com.logistica.logisCode.service.IChoferService;
import com.logistica.logisCode.service.IVehiculoService;
import com.logistica.logisCode.service.IViajeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private IViajeService iViaSer;

    @Autowired
    private IChoferService iChoSer;

    @Autowired
    private IVehiculoService iVehiSer;

    @GetMapping("/getAll")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Viaje>> findAll(){
        List<Viaje> viajeList = iViaSer.findAll();
        return ResponseEntity.ok(viajeList);
    }

    @PostMapping("/save")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> save(@RequestBody Viaje viaje) {

        Long id_Chofer = viaje.getUnChofer().getId();
        System.out.println(id_Chofer);
        Long id_Vehi = viaje.getUnVehiculo().getId();
        System.out.println(id_Vehi);

        Optional<Chofer> readChofer = iChoSer.findById(viaje.getUnChofer().getId());
        Optional<Vehiculo> readVehiculo = iVehiSer.findById(viaje.getUnVehiculo().getId());

        if(readChofer.isPresent()) {
            if(readVehiculo.isPresent()){
                Viaje newViaje = iViaSer.save(viaje);
                return ResponseEntity.ok(newViaje);
            }else {
                return ResponseEntity.badRequest().body("el vehiculo no existe!");
            }

        }else{
            return ResponseEntity.badRequest().body("el chofer no existe!");
        }


    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Viaje> deleteById(@PathVariable Long id){

        try {
            iViaSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Viaje> findById(@PathVariable Long id) {
        Optional<Viaje> viaje = iViaSer.findById(id);
        return viaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Viaje> update(Viaje viaje) {
        Viaje newViaje = iViaSer.update(viaje);
        return ResponseEntity.ok(newViaje);
    }

}