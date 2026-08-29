package dao;
import java.util.List;
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
	public List<Cocinero> traerCocineros() throws HibernateException {
		List<Cocinero> lista=null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Cocinero c order by c.apellido asc, c.nombre asc", Cocinero.class).list();
		} finally {
			session.close();
		}
		return lista;
	}
	public List<Cocinero> traerCocinerosPorEspecialidad(String especialidad) throws HibernateException {
		List<Cocinero> lista=null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Cocinero c where c.especialidad =:especialidad", Cocinero.class).setParameter("especialidad", especialidad).list();
		} finally {
			session.close();
		}
		return lista;
	}
}