/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Encargados;
import java.util.List;
import javax.ejb.Local;


@Local
public interface EncargadosFacadeLocal {

    void create(Encargados encargados);

    void edit(Encargados encargados);

    void remove(Encargados encargados);

    Encargados find(Object id);

    List<Encargados> findAll();

    List<Encargados> findRange(int[] range);

    int count();
    
}
