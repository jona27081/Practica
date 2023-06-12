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
import org.uv.ExamenDemo.Entitys.DTOAlumno;
import org.uv.ExamenDemo.Entitys.DTOGrupos;
import org.uv.ExamenDemo.Entitys.DTOMaterias;
import org.uv.ExamenDemo.Repositorys.AlumnosRepository;
import org.uv.ExamenDemo.Repositorys.GruposRepository;
import org.uv.ExamenDemo.Repositorys.MateriasRepository;

/**
 *
 * @author zS20006736
 */
@RestController
@RequestMapping("/api/alumnos")
public class AlumnosController {

    @Autowired
    AlumnosRepository aRepository;
    @Autowired
    MateriasRepository mRepository;
    @Autowired
    GruposRepository gRepository;

    @GetMapping("/")
    public ResponseEntity<List<DTOAlumno>> getAllAlumnos() {
        try {
            List<DTOAlumno> ltsAlumns = new ArrayList<>();
            aRepository.findAll().forEach(ltsAlumns::add);
            return new ResponseEntity<>(ltsAlumns, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOAlumno> getAlumnoById(@PathVariable("id") String id) {
        Optional<DTOAlumno> almData = aRepository.findById(id);
        if (almData.isPresent()) {
            return new ResponseEntity<>(almData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DTOAlumno> createAlumno(@RequestBody DTOAlumno alm) {
            try {
                DTOAlumno _alm = aRepository.save(new DTOAlumno(
                        alm.getNombre(),
                        alm.getDireccion(),
                        alm.getTelefono()
                ));
                return new ResponseEntity<>(_alm, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAlumno(@PathVariable("id") long id) {
        try {
            aRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOAlumno> updateAlumno(@PathVariable("id") long id, @RequestBody DTOAlumno alm) {
        Optional<DTOAlumno> almData = aRepository.findById(id);
        if (almData.isPresent()) {
            DTOAlumno _alm = almData.get();
            _alm.setNombre(alm.getNombre());
            _alm.setDireccion(alm.getDireccion());
            _alm.setTelefono(alm.getTelefono());
            return new ResponseEntity<>(aRepository.save(_alm), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }*/

}
