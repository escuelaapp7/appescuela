/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.PorcentajeEvaluacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface PorcentajeEvaluacionFacadeLocal {

    void create(PorcentajeEvaluacion porcentajeEvaluacion);

    void edit(PorcentajeEvaluacion porcentajeEvaluacion);

    void remove(PorcentajeEvaluacion porcentajeEvaluacion);

    PorcentajeEvaluacion find(Object id);

    List<PorcentajeEvaluacion> findAll();

    List<PorcentajeEvaluacion> findRange(int[] range);

    int count();
    
}
