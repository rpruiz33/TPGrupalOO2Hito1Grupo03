package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import datos.Festival;
import datos.UnidadVenta;

public class FestivalDao {

	private static Session session;
	private Transaction tx;

	private static FestivalDao dao = null;

	
	protected FestivalDao() { }
	
	public static FestivalDao getIntancia() {
		if (dao == null) {
			dao = new FestivalDao();
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

	public int agregar(Festival objeto) {
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

	public void actualizar(Festival objeto) {
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

	public void eliminar(Festival objeto) {
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

	public Festival traerFestival(long idFestival) {
		Festival objeto = null;
		try {
			iniciaOperacion();
			objeto = (Festival) session.get(Festival.class, idFestival);
		} finally {
			session.close();
		}
		return objeto;
	}
	public Festival traerFestival(String nombre) {
		Festival objeto = null;
		try {
			iniciaOperacion();
			objeto = (Festival) session.createQuery("from Festival f where f.nombre =:nombre")
					.setParameter("nombre", nombre).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
	
	public Set<UnidadVenta> traerUnidadesVentaPorStaffDeFestival(long minStaff) {
	    Set<UnidadVenta> lista = null;
	    try {
	        iniciaOperacion();
	        
	        String hql = "from UnidadVenta u "
	                   + "where exists ("
	                   + "    from Festival f "
	                   + "    join f.unidadesHabilitadas u2 "
	                   + "    join f.staffGeneral s "
	                   + "    where u2 = u "
	                   + "    group by f.id "
	                   + "    having count(s) >= :minStaff"
	                   + ")";

	        lista = new java.util.HashSet<>(
	            session.createQuery(hql, UnidadVenta.class)
	                   .setParameter("minStaff", minStaff)
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

	