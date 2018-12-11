/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Alumnos;
import java.util.List;
import javax.ejb.Local;


@Local
public interface AlumnosFacadeLocal {

    void create(Alumnos alumnos);

    void edit(Alumnos alumnos);

    void remove(Alumnos alumnos);

    Alumnos find(Object id);

    List<Alumnos> findAll();

    List<Alumnos> findRange(int[] range);

    int count();
    
}
