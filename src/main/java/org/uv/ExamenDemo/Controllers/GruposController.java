/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.ExamenDemo.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.ExamenDemo.Entitys.DTOGrupos;
import org.uv.ExamenDemo.Repositorys.GruposRepository;

/**
 *
 * @author zS20006736
 */
@RestController
@RequestMapping("/api/grupos")
public class GruposController {

    @Autowired
    GruposRepository gRepository;

    @GetMapping("/")
    public ResponseEntity<List<DTOGrupos>> getAllGrupos() {
        try {
            List<DTOGrupos> ltsGps = new ArrayList<>();
            gRepository.findAll().forEach(ltsGps::add);
            return new ResponseEntity<>(ltsGps, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOGrupos> getGrupoById(@PathVariable("id") String id) {
        Optional<DTOGrupos> gpsData = gRepository.findById(id);
        if (gpsData.isPresent()) {
            return new ResponseEntity<>(gpsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DTOGrupos> createGrupo(@RequestBody DTOGrupos gps) {
        try {
            DTOGrupos _gps = gRepository.save(new DTOGrupos(
                    gps.getNombreGrupo()
            ));
            return new ResponseEntity<>(_gps, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGrupo(@PathVariable("id") String id) {
        try {
            gRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOGrupos> updateGrupo(@PathVariable("id") String id, @RequestBody DTOGrupos gps) {
        Optional<DTOGrupos> gpsData = gRepository.findById(id);
        if (gpsData.isPresent()) {
            DTOGrupos _gps = gpsData.get();
            _gps.setNombreGrupo(gps.getNombreGrupo());
            return new ResponseEntity<>(gRepository.save(_gps), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
