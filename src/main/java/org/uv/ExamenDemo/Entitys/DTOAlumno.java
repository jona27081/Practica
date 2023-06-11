/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.ExamenDemo.Entitys;

import java.util.List;
import java.util.Random;
import javax.persistence.*;
import org.hibernate.annotations.GeneratorType;

/**
 *
 * @author zS20006736
 */
@Entity
@Table(name = "alumnos")
public class DTOAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clave")
    private String clave;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "clave_grupo")
    private DTOGrupos grupo;

    @ManyToMany
    @JoinTable(name = "alumnos_grupo_materias",
            joinColumns = @JoinColumn(name = "clave_alumno"),
            inverseJoinColumns = @JoinColumn(name = "clave_materia"))
    private List<DTOMaterias> materias;

    @Transient
    private String nombreGrupo;

    @Transient
    private String idMateria;

    public DTOAlumno() {
    }

    public DTOAlumno(String nombre, String direccion, String telefono, DTOGrupos grupo, List<DTOMaterias> materias) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.grupo = grupo;
        this.materias = materias;
    }

    @PrePersist()
    public void generetedClave() {
        String[] nameParts = getNombre().split(" ");
        String firstName = nameParts[0].toUpperCase();
        String randomNumbers = String.format("%03d", random.nextInt(100));
        this.setClave(firstName + randomNumbers);
    }

    public DTOGrupos getGrupo() {
        return grupo;
    }

    public void setGrupo(DTOGrupos grupo) {
        this.grupo = grupo;
    }

    public List<DTOMaterias> getMaterias() {
        return materias;
    }

    public void setMaterias(List<DTOMaterias> materias) {
        this.materias = materias;
    }
    
    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    private static final Random random = new Random();

}
