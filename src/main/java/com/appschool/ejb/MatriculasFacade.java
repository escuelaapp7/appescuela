/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Alumnos;
import com.appschool.model.Matriculas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MatriculasFacade extends AbstractFacade<Matriculas> implements MatriculasFacadeLocal {

    @PersistenceContext(unitName = "appschool_APPSchool_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatriculasFacade() {
        super(Matriculas.class);
    }

    @Override
    public List<Matriculas> validarMismoAlumnoEnElAnio(Alumnos alumno, Integer anio) {
        List<Matriculas> list = null;
        Query q = em.createNativeQuery("select * from (select * from matriculas where matriculas.anio=?1) as matriculas\n"
                + "where matriculas.id_alumno=?2", Matriculas.class);
        q.setParameter(1, anio);
        q.setParameter(2, alumno.getIdAlumno());

        list = q.getResultList();
        return list;
    }

}
