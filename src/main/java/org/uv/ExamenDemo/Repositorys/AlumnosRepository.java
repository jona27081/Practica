/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package org.uv.ExamenDemo.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.ExamenDemo.Entitys.DTOAlumno;



/**
 *
 * @author zS20006736
 */
public interface AlumnosRepository extends JpaRepository<DTOAlumno, String> {
    
}
