/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Alumnos;
import com.appschool.model.Matriculas;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MatriculasFacadeLocal {

    void create(Matriculas matriculas);

    void edit(Matriculas matriculas);

    void remove(Matriculas matriculas);

    Matriculas find(Object id);

    List<Matriculas> findAll();

    List<Matriculas> findRange(int[] range);

    List<Matriculas> validarMismoAlumnoEnElAnio(Alumnos alumno, Integer anio);

    int count();

}
