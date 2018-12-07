/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Parentezcos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author said
 */
@Local
public interface ParentezcosFacadeLocal {

    void create(Parentezcos parentezcos);

    void edit(Parentezcos parentezcos);

    void remove(Parentezcos parentezcos);

    Parentezcos find(Object id);

    List<Parentezcos> findAll();

    List<Parentezcos> findRange(int[] range);

    int count();
    
}
