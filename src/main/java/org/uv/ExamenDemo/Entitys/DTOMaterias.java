/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.ExamenDemo.Entitys;

import java.util.List;
import java.util.Random;
import javax.persistence.*;

/**
 *
 * @author zS20006736
 */
@Entity
@Table(name = "materias")
public class DTOMaterias {

    @Id
    @Column(name = "clave")
    private String clave;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "creditos")
    private int creditos;


    public DTOMaterias() {
    }

    public DTOMaterias(String nombre, int creditos) {
        this.nombre = nombre;
        this.creditos = creditos;
    }

    @PrePersist()
    public void generatedClave() {
        String n = getNombre().substring(0, Math.min(getNombre().length(), 2)).toUpperCase();
        String randomNumbers = String.format("%03d", random.nextInt(100));
        this.setClave(n + randomNumbers);
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

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    private static final Random random = new Random();
}
