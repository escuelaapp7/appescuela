/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Grados;
import java.util.List;
import javax.ejb.Local;


@Local
public interface GradosFacadeLocal {

    void create(Grados grados);

    void edit(Grados grados);

    void remove(Grados grados);

    Grados find(Object id);

    List<Grados> findAll();

    List<Grados> findRange(int[] range);

    int count();
    
}
