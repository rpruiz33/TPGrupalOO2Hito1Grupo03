package dao;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import datos.Cocinero;

public class CocineroDao {
	private static Session session;
	private Transaction tx;
	private static CocineroDao dao = null;
	protected CocineroDao() {}
	public static CocineroDao getIntancia() {
		if(dao == null) {
			dao = new CocineroDao();
		}
		return dao;
	}
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	public int agregar(Cocinero objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	public void actualizar(Cocinero objeto) {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	public void eliminar(Cocinero objeto) {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	public Cocinero traerCocinero(long idStaff) {
		Cocinero objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cocinero) session.get(Cocinero.class, idStaff);
		} finally {
			session.close();
		}
		return objeto;
	}
	public Set<Cocinero> traerCocineros() throws HibernateException {
	    Set<Cocinero> lista = null;
	    try {
	        iniciaOperacion();
	        lista = new java.util.LinkedHashSet<>(
	            session.createQuery("from Cocinero c order by c.apellido asc, c.nombre asc", Cocinero.class)
	                   .getResultList()
	        );
	    } finally {
	        session.close();
	    }
	    return lista;
	}
	public Set<Cocinero> traerCocinerosPorEspecialidad(String especialidad) throws HibernateException {
	    Set<Cocinero> lista = null;
	    try {
	        iniciaOperacion();
	        lista = new java.util.HashSet<>(
	            session.createQuery("from Cocinero c where c.especialidad =:especialidad", Cocinero.class)
	                   .setParameter("especialidad", especialidad)
	                   .getResultList()
	        );
	    } finally {
	        session.close();
	    }
	    return lista;
	}

	public Set<Cocinero> traerCocinerosPorFestivalYFechas(Long idFestival, LocalDate fechaInicio, LocalDate fechaFin) throws HibernateException {
	    Set<Cocinero> lista = null;
	    try {
	        iniciaOperacion();
	        
	        String hql = "select distinct c from Cocinero c "
	                   + "where exists ("
	                   + "    from Festival f "
	                   + "    join f.staffGeneral sg "
	                   + "    where sg = c "
	                   + "    and f.id = :idFestival "
	                   + "    and f.fechaInicio >= :fechaInicioBuscada "
	                   + "    and f.fechaFin <= :fechaFinBuscada"
	                   + ") or exists ("
	                   + "    from Festival f "
	                   + "    join f.unidadesHabilitadas u "
	                   + "    join u.staffPuesto sp "
	                   + "    where sp = c "
	                   + "    and f.id = :idFestival "
	                   + "    and f.fechaInicio >= :fechaInicioBuscada "
	                   + "    and f.fechaFin <= :fechaFinBuscada"
	                   + ")";

	        lista = new java.util.HashSet<>(
	            session.createQuery(hql, Cocinero.class)
	                   .setParameter("idFestival", idFestival)
	                   .setParameter("fechaInicioBuscada", fechaInicio)
	                   .setParameter("fechaFinBuscada", fechaFin)
	                   .getResultList()
	        );

	    } catch (HibernateException he) {
	        manejaExcepcion(he);
	        throw he;
	    } finally {
	        session.close();
	    }
	    return lista;
	}
}