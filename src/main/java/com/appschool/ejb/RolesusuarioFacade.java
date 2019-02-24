/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Rolesusuario;
import com.appschool.model.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RolesusuarioFacade extends AbstractFacade<Rolesusuario> implements RolesusuarioFacadeLocal {

    @PersistenceContext(unitName = "appschool_APPSchool_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesusuarioFacade() {
        super(Rolesusuario.class);
    }

    @Override
    public List<Rolesusuario> rolesPorUsuario(Usuarios usuario) {
        List<Rolesusuario> lista = null;
        try {

            Query q = em.createNativeQuery("select * "
                    + "from roles_usuario \n"
                    + "where roles_usuario.id_usuario = ?1", Rolesusuario.class);
            q.setParameter(1, usuario.getIdUsuario());
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }

}
