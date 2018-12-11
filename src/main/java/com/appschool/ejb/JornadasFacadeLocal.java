/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Jornadas;
import java.util.List;
import javax.ejb.Local;


@Local
public interface JornadasFacadeLocal {

    void create(Jornadas jornadas);

    void edit(Jornadas jornadas);

    void remove(Jornadas jornadas);

    Jornadas find(Object id);

    List<Jornadas> findAll();

    List<Jornadas> findRange(int[] range);

    int count();
    
}
