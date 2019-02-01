/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.ejb;

import com.appschool.model.Calificaciones;
import com.appschool.model.Impartir;
import com.appschool.model.Matriculas;
import com.appschool.model.Usuarios;
import java.util.Calendar;
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
                    + "where coordinador_grado.id_grado = ?1 and coordinador_grado.id_seccion = ?2 group by  id_matricula", Matriculas.class);
            q.setParameter(1, impartir.getIdGrado().getIdGrado());
            q.setParameter(2, impartir.getIdSeccion().getIdSeccion());

            lstMatriculas = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMatriculas;

    }

    @Override
    public List<Impartir> impartirPorUsuario(Usuarios usuario) {
        List<Impartir> lista = null;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        try {
            Query q = em.createNativeQuery("select \n"
                    + "impartir.id_profesor, id_asignatura, id_grado, \n"
                    + "id_seccion, id_jornada, id_impartir\n"
                    + "from \n"
                    + "impartir \n"
                    + "inner join profesores on profesores.id_profesor = impartir.id_profesor\n"
                    + "inner join personas on profesores.id_persona = personas.id_persona \n"
                    + "where\n"
                    + "personas.id_persona = ?1 and impartir.anio=" + year + " or impartir.anio=" + (year + 1) + "", Impartir.class);
            q.setParameter(1, usuario.getIdPersona().getIdPersona());
            lista = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Calificaciones> obtenerCalificacionesPorMateria(Matriculas matricula, Impartir impartir) {
        List<Calificaciones> lista = null;
        try {
            Query q = em.createNativeQuery("select \n"
                    + "*\n"
                    + "from \n"
                    + "(select * from calificaciones where id_matricula=?1) as notas\n"
                    + "where \n"
                    + "id_impartir=?2 or id_impartir is null", Calificaciones.class);
            q.setParameter(1, matricula.getIdMatricula());
            q.setParameter(2, impartir.getIdImpartir());
            lista = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
