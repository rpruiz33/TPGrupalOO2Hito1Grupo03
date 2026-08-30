package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.FoodTrack;

public class FoodTrackDao {

	private static Session session;
	private Transaction tx;

	private static FoodTrackDao dao = null;

	protected FoodTrackDao() { }

	public static FoodTrackDao getIntancia() {
		if (dao == null) {
			dao = new FoodTrackDao();
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

	public int agregar(FoodTrack objeto) {
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

	public void actualizar(FoodTrack objeto) {
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

	public void eliminar(FoodTrack objeto) {
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

	public FoodTrack traerFoodTrack(long idUnidadVenta) {
		FoodTrack objeto = null;
		try {
			iniciaOperacion();
			objeto = (FoodTrack) session.get(FoodTrack.class, idUnidadVenta);
		} finally {
			session.close();
		}
		return objeto;
	}

	public FoodTrack traerFoodTrackPorCodigo(String codigo) {
		FoodTrack objeto = null;
		try {
			iniciaOperacion();
			objeto = (FoodTrack) session.createQuery("from FoodTrack f where f.codigo =:codigo")
					.setParameter("codigo", codigo).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<FoodTrack> traerFoodTracks() throws HibernateException {
		List<FoodTrack> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from FoodTrack f order by f.nombreComercial asc", FoodTrack.class).list();
		} finally {
			session.close();
		}
		return lista;
	}

	public List<FoodTrack> traerFoodTracksPorElectricidad(boolean requiereElectricidad) throws HibernateException {
		List<FoodTrack> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from FoodTrack f where f.requiereElectricidad =:req", FoodTrack.class)
					.setParameter("req", requiereElectricidad).list();
		} finally {
			session.close();
		}
		return lista;
	}
}
