/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Profesores;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface ProfesoresFacadeLocal {

    void create(Profesores profesores);

    void edit(Profesores profesores);

    void remove(Profesores profesores);

    Profesores find(Object id);

    List<Profesores> findAll();

    List<Profesores> findRange(int[] range);

    int count();
    
}
