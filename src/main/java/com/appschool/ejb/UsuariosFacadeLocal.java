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
import javax.ejb.Local;

@Local
public interface UsuariosFacadeLocal {

    void create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    public Usuarios iniciarSesion(Usuarios us);

    List<Rolesusuario> rolesPorUsuario(Usuarios usuario);

    List<Menurol> menuPorRoles(Roles rol);

    int count();

}
