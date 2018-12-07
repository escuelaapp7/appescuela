/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author said
 */
@Entity
@Table(name = "Menu_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menurol.findAll", query = "SELECT m FROM Menurol m")
    , @NamedQuery(name = "Menurol.findByIdMenuRol", query = "SELECT m FROM Menurol m WHERE m.idMenuRol = :idMenuRol")})
public class Menurol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu_rol")
    private Integer idMenuRol;
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    @ManyToOne(optional = false)
    private Menus idMenu;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Roles idRol;

    public Menurol() {
    }

    public Menurol(Integer idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public Integer getIdMenuRol() {
        return idMenuRol;
    }

    public void setIdMenuRol(Integer idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public Menus getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menus idMenu) {
        this.idMenu = idMenu;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuRol != null ? idMenuRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menurol)) {
            return false;
        }
        Menurol other = (Menurol) object;
        if ((this.idMenuRol == null && other.idMenuRol != null) || (this.idMenuRol != null && !this.idMenuRol.equals(other.idMenuRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Menurol[ idMenuRol=" + idMenuRol + " ]";
    }
    
}
