/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Impartir;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ImpartirFacade extends AbstractFacade<Impartir> implements ImpartirFacadeLocal {

    @PersistenceContext(unitName = "appschool_APPSchool_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpartirFacade() {
        super(Impartir.class);
    }
    
}
