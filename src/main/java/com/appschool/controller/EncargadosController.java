/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.EncargadosFacadeLocal;
import com.appschool.model.Encargados;
import com.appschool.model.Parentezcos;
import com.appschool.model.Personas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class EncargadosController implements Serializable {

    private Encargados encargado;
    private Parentezcos parentezco;
    private Personas persona;
    @EJB
    private EncargadosFacadeLocal encargadoEJB;
    private List<Encargados> lstEncargados;
 
    @PostConstruct
    public void init() {
        persona= new Personas();
        encargado = new Encargados();
        parentezco =  new Parentezcos();
        lstEncargados = encargadoEJB.findAll();
    }
    
    public void operar(){
        try {
            if(encargado.getIdEncargado()== null){
                encargado.setIdPersona(persona);
                encargadoEJB.create(encargado);
            }else{
                 encargadoEJB.edit(encargado);
            }
        } catch (Exception e) {
        }
        
    }
    
    public void eliminar(){
        try {
            encargadoEJB.remove(encargado);
        } catch (Exception e) {
        }
    }

    public Encargados getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargados encargado) {
        this.encargado = encargado;
    }

    public Parentezcos getParentezco() {
        return parentezco;
    }

    public void setParentezco(Parentezcos parentezco) {
        this.parentezco = parentezco;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public List<Encargados> getLstEncargados() {
        return lstEncargados;
    }

    public void setLstEncargados(List<Encargados> lstEncargados) {
        this.lstEncargados = lstEncargados;
    }
    
    
}
