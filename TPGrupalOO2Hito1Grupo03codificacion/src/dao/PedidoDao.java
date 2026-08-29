package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Pedido;

public class PedidoDao {

	private static Session session;
	private Transaction tx;

	private static PedidoDao dao = null;

	
	protected PedidoDao() {}

	
	public static PedidoDao getIntancia() {
		if (dao == null) {
			dao = new PedidoDao();
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

	public int agregar(Pedido objeto) {
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

	public void actualizar(Pedido objeto) {
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

	public void eliminar(Pedido objeto) {
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

	public Pedido traerPedido(long id) {
		Pedido objeto = null;
		try {
			iniciaOperacion();
			objeto = (Pedido) session.get(Pedido.class, id);
		} finally {
			session.close();
		}
		return objeto;
	}
	public List<Pedido> traerPedido() throws HibernateException {
		List<Pedido> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Pedido p order by p.fecha desc", Pedido.class).list();
		} finally {
			session.close();
		}
		return lista;
	}
	public List<Pedido> traerPedidoPorFestival(long idFestival) throws HibernateException {
		List<Pedido> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Pedido p where p.festival.idFestival =:idFestival", Pedido.class)
					.setParameter("idFestival", idFestival).list();
		} finally {
			session.close();
		}
		return lista;
	}
	public Pedido traerPedidoYItems(long id) {
		Pedido objeto = null;
		try {
			iniciaOperacion();
			String hql = "from Pedido p where p.id =:id";
			objeto = (Pedido) session.createQuery(hql).setParameter("id", id).uniqueResult();
			Hibernate.initialize(objeto.getItems());
		} finally {
			session.close();
		}
		return objeto;
	}
	public List<Pedido> traerPedidoPorUnidadVenta(long idunidadVenta) {
		List<Pedido> lista = null;
		try {
			iniciaOperacion();
			String hql = "from Pedido p where p.unidadVenta = :unidadVenta";
			lista = session.createQuery(hql).setParameter("unidadVenta", idunidadVenta).list();
		} finally {
			session.close();
		}
		return lista;
	}
}
