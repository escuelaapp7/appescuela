/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Coordinadorgrado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface CoordinadoresFacadeLocal {

    void create(Coordinadorgrado coordinador);

    void edit(Coordinadorgrado coordinador);

    void remove(Coordinadorgrado coordinador);

    Coordinadorgrado find(Object id);

    List<Coordinadorgrado> findAll();

    List<Coordinadorgrado> findRange(int[] range);

    int count();
}
