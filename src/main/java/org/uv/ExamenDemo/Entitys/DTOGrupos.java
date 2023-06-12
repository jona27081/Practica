/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.ExamenDemo.Entitys;

import java.util.Random;
import javax.persistence.*;

/**
 *
 * @author zS20006736
 */
@Entity()
@Table(name = "grupos")
public class DTOGrupos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clave;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "clave_materia")
    private DTOMaterias materias;

    @ManyToOne
    @JoinColumn(name = "clave_alumno")
    private DTOAlumno alumno;

    public DTOGrupos(String nombre, DTOMaterias materias, DTOAlumno alumno) {
        this.nombre = nombre;
        this.materias = materias;
        this.alumno = alumno;
    }


    public DTOGrupos() {
    }

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DTOMaterias getMaterias() {
        return materias;
    }

    public void setMaterias(DTOMaterias materias) {
        this.materias = materias;
    }

    public DTOAlumno getAlumno() {
        return alumno;
    }

    public void setAlumno(DTOAlumno alumno) {
        this.alumno = alumno;
    }

    

    private static final Random random = new Random();
}
