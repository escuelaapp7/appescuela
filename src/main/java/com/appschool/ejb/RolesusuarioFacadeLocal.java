/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Rolesusuario;
import java.util.List;
import javax.ejb.Local;


@Local
public interface RolesusuarioFacadeLocal {

    void create(Rolesusuario rolesusuario);

    void edit(Rolesusuario rolesusuario);

    void remove(Rolesusuario rolesusuario);

    Rolesusuario find(Object id);

    List<Rolesusuario> findAll();

    List<Rolesusuario> findRange(int[] range);

    int count();
    
}
