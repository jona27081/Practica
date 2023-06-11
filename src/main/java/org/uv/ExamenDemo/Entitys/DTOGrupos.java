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
    @Column(name = "id_grupo")
    private String id;

    @Column(name = "nombre_grupo")
    private String nombreGrupo;

    public DTOGrupos(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    
    public DTOGrupos(){
    }

    @PrePersist()
    public void generatedClave() {
        String n = getNombreGrupo().substring(0, Math.min(getNombreGrupo().length(), 3)).toUpperCase();
        String randomNumbers = String.format("%03d", random.nextInt(100));
        this.setId(n + randomNumbers);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    
    private static final Random random = new Random();
}
