/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.ExamenDemo.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.uv.ExamenDemo.Entitys.CreacionGrupos;
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
@RequestMapping("/api/grupos")
public class GruposController {

    @Autowired
    GruposRepository gRepository;
    @Autowired
    AlumnosRepository aRepository;
    @Autowired
    MateriasRepository mRepository;

    public ResponseEntity<List<DTOMaterias>> checkIdsExistMateria(List<String> mats) {
        List<DTOMaterias> ltsMaterias = new ArrayList<>();
        try {
            for (String mat : mats) {
                Optional<DTOMaterias> mOptional = mRepository.findById(mat);
                if (mOptional.isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
                ltsMaterias.add(mOptional.get());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(ltsMaterias);
    }

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

    public ResponseEntity<List<DTOAlumno>> checkIdsExistAlumno(List<String> alumnos) {
        List<DTOAlumno> ltsAlumnos = new ArrayList<>();
        try {
            for (String alm : alumnos) {
                Optional<DTOAlumno> mOptional = aRepository.findById(alm);
                if (mOptional.isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
                ltsAlumnos.add(mOptional.get());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(ltsAlumnos);
    }

    @PostMapping("/")
    public ResponseEntity<DTOGrupos> createGrupo(@RequestBody CreacionGrupos dataGrupo) {
        List<DTOAlumno> ltsAlumnos = checkIdsExistAlumno(dataGrupo.getAlumnos()).getBody();
        List<DTOMaterias> ltsMaterias = checkIdsExistMateria(dataGrupo.getMaterias()).getBody();
        if (ltsAlumnos != null && ltsMaterias != null && !ltsAlumnos.isEmpty() && !ltsMaterias.isEmpty()) {
            try {
                for (DTOAlumno alm : ltsAlumnos) {
                    for (DTOMaterias mat : ltsMaterias) {
                        gRepository.save(new DTOGrupos(
                                dataGrupo.getNombre(),
                                mat,
                                alm
                        ));
                    }
                }
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
    }

        /*@DeleteMapping("/{id}")
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
    }*/
    }
