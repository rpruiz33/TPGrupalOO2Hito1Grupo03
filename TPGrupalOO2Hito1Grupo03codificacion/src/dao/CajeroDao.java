package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cajero;

public class CajeroDao {

	private static Session session;
	private Transaction tx;

	private static CajeroDao dao = null;

	protected CajeroDao() { }

	public static CajeroDao getIntancia() {
		if (dao == null) {
			dao = new CajeroDao();
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

	public int agregar(Cajero objeto) {
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

	public void actualizar(Cajero objeto) {
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

	public void eliminar(Cajero objeto) {
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

	public Cajero traerCajero(long idStaff) {
		Cajero objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cajero) session.get(Cajero.class, idStaff);
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<Cajero> traerCajeros() throws HibernateException {
		List<Cajero> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Cajero c order by c.apellido asc, c.nombre asc", Cajero.class).list();
		} finally {
			session.close();
		}
		return lista;
	}

	public List<Cajero> traerCajerosPorTurno(String turno) throws HibernateException {
		List<Cajero> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Cajero c where c.turno =:turno", Cajero.class)
					.setParameter("turno", turno).list();
		} finally {
			session.close();
		}
		return lista;
	}
}
