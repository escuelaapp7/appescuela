/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Menurol;
import com.appschool.model.Menus;
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

    @Override
    public void crearMenusPrimeraVez() {
        try {
            Query q = em.createNativeQuery("INSERT INTO public.menus(\n"
                    + "	id_menu, icono, nombre, tipo, url, codigo_submenu)\n"
                    + "	VALUES (1, '', 'Administracion','S', '',null),\n"
                    + "	           (2, 'fa fa-w fas fa-users', 'Registro de Usuarios','I', '/administracion/usuarios.xhtml',1),\n"
                    + "		   (3, 'fa fa-w fas fa-project-diagram', 'Registro de roles','I', '/administracion/roles.xhtml',1),\n"
                    + "		   (4, 'fa fa-w fas fa-universal-access', 'Roles por Usuarios','I', '/administracion/asignarRoles.xhtml',1),\n"
                    + "		   (5, 'fa fa-w fas fa-desktop', 'Registrar Menu','I', '/administracion/menus.xhtml',1),\n"
                    + "		   (6, 'fa fa-w fas fa-lock', 'Accesos por roles','I', '/administracion/asignarMenuRol.xhtml',1),\n"
                    + "		   (7, '', 'Catalogos','S','',null),\n"
                    + "		   (8, 'fa fa-w far fa-handshake', 'Parentezcos','I', '/catalogos/parentezcos.xhtml',7),\n"
                    + "		   (9, 'fa fa-w fas fa-audio-description', 'Secciones','I', '/catalogos/secciones.xhtml',7),\n"
                    + "		   (10, 'fa fa-w far fa-sun', 'Jornadas','I', '/catalogos/jornadas.xhtml',7),\n"
                    + "		   (11, '', 'Registros','S','',null),\n"
                    + "		   (12, 'fa fa-w fa-chalkboard-teacher', 'Profesores','I', '/registro/profesores.xhtml',11),\n"
                    + "		   (13, 'fa fa-w fas fa-child', 'Alumnos','I', '/registro/alumnos.xhtml',11),\n"
                    + "		   (14, 'fa fa-w fas fa-diagnoses', 'Coordinadores','I', '/registro/coordinadores.xhtml',11),\n"
                    + "		   (15, 'fa fa-w fas fa-file-alt', 'Matricula','I', '/registro/matriculaW.xhtml',11),\n"
                    + "		   (16, 'fa fa-w fa-book', 'Materias por Maestro','I', '/registro/asignacionMaterias.xhtml',11),\n"
                    + "		   (17, 'fa fa-w fas fa-grip-vertical', 'Calificaciones','I', '/registro/calificaciones.xhtml',11);");
            q.executeUpdate();

            Query q1 = em.createNativeQuery("INSERT INTO public.personas(\n"
                    + "	id_persona, apellidos, direccion, fecha_nacimiento, nombres, sexo, telefono)\n"
                    + "	VALUES (1, 'Administrador', 'Administrador', null, 'Administrador', 'Administrador', 121321);");
            q1.executeUpdate();

            Query q2 = em.createNativeQuery("INSERT INTO public.usuarios(\n"
                    + "	id_usuario, estado, password, usuario, id_persona)\n"
                    + "	VALUES (1, null, 'ceme2019', 'admin', 1);");
            q2.executeUpdate();

            Query q3 = em.createNativeQuery("INSERT INTO public.roles(\n"
                    + "	id_rol, descripcion, nombre)\n"
                    + "	VALUES (1, 'Administrador', 'Administrador');");
            q3.executeUpdate();

            Query q4 = em.createNativeQuery("INSERT INTO public.roles_usuario(\n"
                    + "	id_rol_usuario, id_rol, id_usuario)\n"
                    + "	VALUES (1, 1, 1);");
            q4.executeUpdate();

            Query q5 = em.createNativeQuery("INSERT INTO public.menu_rol(\n"
                    + "	id_menu_rol, id_menu, id_rol)\n"
                    + "	VALUES (1, 1, 1),\n"
                    + "		   (2, 2, 1),\n"
                    + "		   (3, 3, 1),\n"
                    + "		   (4, 4, 1),\n"
                    + "		   (5, 5, 1),\n"
                    + "		   (6, 6, 1),\n"
                    + "		   (7, 7, 1),\n"
                    + "		   (8, 8, 1),\n"
                    + "		   (9, 9, 1),\n"
                    + "		   (10, 10, 1),\n"
                    + "		   (11, 11, 1),\n"
                    + "		   (12, 12, 1),\n"
                    + "		   (13, 13, 1),\n"
                    + "		   (14, 14, 1),\n"
                    + "		   (15, 15, 1),\n"
                    + "		   (16, 16, 1),\n"
                    + "		   (17, 17, 1);");
            q5.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
