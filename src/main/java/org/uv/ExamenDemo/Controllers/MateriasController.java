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
import org.uv.ExamenDemo.Entitys.DTOMaterias;
import org.uv.ExamenDemo.Repositorys.MateriasRepository;

/**
 *
 * @author zS20006736
 */
@RestController
@RequestMapping("/api/materias")
public class MateriasController {
    @Autowired
    MateriasRepository mRepository;

    @GetMapping("/")
    public ResponseEntity<List<DTOMaterias>> getAllMaterias() {
        try {
            List<DTOMaterias> ltsMat = new ArrayList<>();
            mRepository.findAll().forEach(ltsMat::add);
            return new ResponseEntity<>(ltsMat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOMaterias> getMateriaById(@PathVariable("id") String id) {
        Optional<DTOMaterias> matData = mRepository.findById(id);
        if (matData.isPresent()) {
            return new ResponseEntity<>(matData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DTOMaterias> createMateria(@RequestBody DTOMaterias mat) {
        try {
            DTOMaterias _mat = mRepository.save(new DTOMaterias(
                    mat.getNombre(),
                    mat.getCreditos()
            ));
            return new ResponseEntity<>(_mat, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMateria(@PathVariable("id") String id) {
        try {
            mRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DTOMaterias> updateMateria(@PathVariable("id") String id, @RequestBody DTOMaterias mat) {
        Optional<DTOMaterias> matData = mRepository.findById(id);
        if(matData.isPresent()){
            DTOMaterias _mat = matData.get();
            _mat.setNombre(mat.getNombre());
            _mat.setCreditos(mat.getCreditos());
            return new ResponseEntity<>(mRepository.save(_mat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
}

