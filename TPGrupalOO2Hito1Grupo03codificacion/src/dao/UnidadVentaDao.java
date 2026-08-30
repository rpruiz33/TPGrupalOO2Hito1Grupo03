package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.UnidadVenta;

public class UnidadVentaDao {

	private static Session session;
	private Transaction tx;

	private static UnidadVentaDao dao = null;

	
	protected UnidadVentaDao() { }

	public static UnidadVentaDao getIntancia() {
		if (dao == null) {
			dao = new UnidadVentaDao();
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


	public long agregar(UnidadVenta objeto) {
	    long id = 0;
	    try {
	        iniciaOperacion();
	        id = (long) session.save(objeto);
	        tx.commit();
	    } catch (HibernateException he) {
	        manejaExcepcion(he);
	        throw he;
	    } finally {
	        session.close();
	    }
	    return id;
	}

	public void actualizar(UnidadVenta objeto) {
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

	public void eliminar(UnidadVenta objeto) {
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
	
	public UnidadVenta traer(long idUnidadVenta) {
	    UnidadVenta objeto = null;
	    try {
	        iniciaOperacion();
	        objeto = session.get(UnidadVenta.class, idUnidadVenta);
	    } finally {
	        session.close();
	    }
	    return objeto;
	}

	public Set<UnidadVenta> traerUnidadesVentaPorDatosStaff(String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso) throws HibernateException {
	    Set<UnidadVenta> lista = null;
	    try {
	        iniciaOperacion();
	        String hql = "select distinct u from UnidadVenta u "
	                   + "left join u.staffPuesto sp "
	                   + "left join u.responsable r "
	                   + "where (sp.dni = :dni and sp.fechaNacimiento = :fechaNacimiento and sp.fechaIngreso = :fechaIngreso) "
	                   + "or (r.dni = :dni and r.fechaNacimiento = :fechaNacimiento and r.fechaIngreso = :fechaIngreso)";

	        lista = new java.util.HashSet<>(
	            session.createQuery(hql, UnidadVenta.class)
	                   .setParameter("dni", dni)
	                   .setParameter("fechaNacimiento", fechaNacimiento)
	                   .setParameter("fechaIngreso", fechaIngreso)
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
	public UnidadVenta traerUnidadVenta(long id) {
		UnidadVenta objeto = null;
		try {
			iniciaOperacion();
			objeto = (UnidadVenta) session.get(UnidadVenta.class, id);
		} finally {
			session.close();
		}
		return objeto;
	}
	public List<UnidadVenta> traerUnidadVentas() throws HibernateException {
		List<UnidadVenta> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from UnidadVenta u order by u.nombreComercial asc", UnidadVenta.class).list();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public List<UnidadVenta> traerUnidadVentasPorResponsable(long idStaff) throws HibernateException {
		List<UnidadVenta> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from UnidadVenta u where u.responsable.id =:idStaff", UnidadVenta.class)
					.setParameter("idStaff", idStaff).list();
		} finally {
			session.close();
		}
		return lista;
	}
	public UnidadVenta traerUnidadVentaConStaff(long id) {
		UnidadVenta objeto = null;
		try {
			iniciaOperacion();
			objeto = (UnidadVenta) session.get(UnidadVenta.class, id);
			if (objeto != null) {
				Hibernate.initialize(objeto.getStaffPuesto());
			}
		} finally {
			session.close();
		}
		return objeto;
	}

}

	