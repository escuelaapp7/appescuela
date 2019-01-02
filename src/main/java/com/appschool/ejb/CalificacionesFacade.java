/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Calificaciones;
import com.appschool.model.Impartir;
import com.appschool.model.Matriculas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CalificacionesFacade extends AbstractFacade<Calificaciones> implements CalificacionesFacadeLocal {

    @PersistenceContext(unitName = "appschool_APPSchool_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionesFacade() {
        super(Calificaciones.class);
    }

    @Override
    public List<Matriculas> alumnosPorAsignacion(Impartir impartir) {
        List<Matriculas> lstMatriculas = null;
        try {
            Query q = em.createNativeQuery("select id_matricula,fecha_matricula,id_alumno,id_encargado,id_parentezco,matriculas.id_coordinador_grado from matriculas\n"
                    + " full outer join coordinador_grado on matriculas.id_coordinador_grado = coordinador_grado.id_coordinador_grado\n"
                    + "inner join grados on coordinador_grado.id_grado = grados.id_grado\n"
                    + "INNER join impartir on grados.id_grado = impartir.id_grado\n"
                    + "where coordinador_grado.id_grado = ?1 and coordinador_grado.id_seccion = ?2", Matriculas.class);
            q.setParameter(1, impartir.getIdGrado().getIdGrado());
            q.setParameter(2, impartir.getIdSeccion().getIdSeccion());

            lstMatriculas = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMatriculas;

    }

}
