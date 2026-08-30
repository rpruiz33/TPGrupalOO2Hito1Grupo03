package dao;

import java.util.List;

import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.PuestoDesarmable;


public class PuestoDesarmableDao {

	private static Session session;
	private Transaction tx;

	private static PuestoDesarmableDao dao = null;

	protected PuestoDesarmableDao() { }

	public static PuestoDesarmableDao getIntancia() {
		if (dao == null) {
			dao = new PuestoDesarmableDao();
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

	public int agregar(PuestoDesarmable objeto) {
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

	public void actualizar(PuestoDesarmable objeto) {
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

	public void eliminar(PuestoDesarmable objeto) {
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

	public PuestoDesarmable traerPuestoDesarmable(long idUnidadVenta) {
		PuestoDesarmable objeto = null;
		try {
			iniciaOperacion();
			objeto = (PuestoDesarmable) session.get(PuestoDesarmable.class, idUnidadVenta);
		} finally {
			session.close();
		}
		return objeto;
	}

	public PuestoDesarmable traerPuestoDesarmablePorCodigo(String codigo) {
		PuestoDesarmable objeto = null;
		try {
			iniciaOperacion();
			objeto = (PuestoDesarmable) session.createQuery("from PuestoDesarmable p where p.codigo =:codigo")
					.setParameter("codigo", codigo).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<PuestoDesarmable> traerPuestosDesarmables() throws HibernateException {
		List<PuestoDesarmable> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from PuestoDesarmable p order by p.nombreComercial asc", PuestoDesarmable.class).list();
		} finally {
			session.close();
		}
		return lista;
	}

	public List<PuestoDesarmable> traerPuestosPorCantidadCarpasMinima(int minimoCarpas) throws HibernateException {
		List<PuestoDesarmable> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from PuestoDesarmable p where p.cantidadCarpas >=:minimo", PuestoDesarmable.class)
					.setParameter("minimo", minimoCarpas).list();
		} finally {
			session.close();
		}
		return lista;
	}
	public Set<PuestoDesarmable> traerPuestosComplejos(int minCarpas, int maxTiempoMontaje, double minSuperficie, long minStaff) throws HibernateException {
	    Set<PuestoDesarmable> lista = null;
	    try {
	        iniciaOperacion();
	        
	        String hql = "from PuestoDesarmable p "
	                   + "where p.cantidadCarpas >= :minCarpas "
	                   + "and p.tiempoMontajeMin <= :maxTiempoMontaje "
	                   + "and p.superficieM2 >= :minSuperficie "
	                   + "and exists ("
	                   + "    from Festival f "
	                   + "    join f.unidadesHabilitadas u "
	                   + "    join f.staffGeneral sg "
	                   + "    where u = p "
	                   + "    group by f.id "
	                   + "    having count(sg) >= :minStaff"
	                   + ") "
	                   + "order by p.nombreComercial asc";

	        lista = new java.util.HashSet<>(
	            session.createQuery(hql, PuestoDesarmable.class)
	                   .setParameter("minCarpas", minCarpas)
	                   .setParameter("maxTiempoMontaje", maxTiempoMontaje)
	                   .setParameter("minSuperficie", minSuperficie)
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
