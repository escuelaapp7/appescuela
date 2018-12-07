/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Periodos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface PeriodosFacadeLocal {

    void create(Periodos periodos);

    void edit(Periodos periodos);

    void remove(Periodos periodos);

    Periodos find(Object id);

    List<Periodos> findAll();

    List<Periodos> findRange(int[] range);

    int count();
    
}
