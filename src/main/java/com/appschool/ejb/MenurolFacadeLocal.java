/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Menurol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface MenurolFacadeLocal {

    void create(Menurol menurol);

    void edit(Menurol menurol);

    void remove(Menurol menurol);

    Menurol find(Object id);

    List<Menurol> findAll();

    List<Menurol> findRange(int[] range);

    int count();
    
}
