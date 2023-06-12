/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.ExamenDemo.Entitys;

import java.util.List;
import java.util.Optional;
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
    @Column(name = "clave")
    private String clave;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

    public DTOAlumno() {
    }

    public DTOAlumno(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @PrePersist()
    public void generetedClave() {
        String[] nameParts = getNombre().split(" ");
        String firstName = nameParts[0].toUpperCase();
        String randomNumbers = String.format("%03d", random.nextInt(100));
        this.setClave(firstName + randomNumbers);
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
