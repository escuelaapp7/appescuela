/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Menurol;
import com.appschool.model.Roles;
import com.appschool.model.Rolesusuario;
import com.appschool.model.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "appschool_APPSchool_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios iniciarSesion(Usuarios us) {
        Usuarios usuario = null;
        try {
            String consulta = "FROM Usuarios u WHERE u.usuario = ?1 and u.password=?2";
            Query q = em.createQuery(consulta);
            q.setParameter(1, us.getUsuario());
            q.setParameter(2, us.getPassword());

            List<Usuarios> lista = q.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    @Override
    public List<Rolesusuario> rolesPorUsuario(Usuarios usuario) {
        List<Rolesusuario> lista = null;
        try {
            Query q = em.createNativeQuery("select * from roles_usuario where id_usuario=?1;", Rolesusuario.class);
            q.setParameter(1, usuario.getIdUsuario());
            lista = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;

    }

    @Override
    public List<Menurol> menuPorRoles(Roles rol) {
        List<Menurol> lista = null;
        try {
            Query q = em.createNativeQuery("select * from menu_rol where id_rol = ?1;", Menurol.class);
            q.setParameter(1, rol.getIdRol());
            lista = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
