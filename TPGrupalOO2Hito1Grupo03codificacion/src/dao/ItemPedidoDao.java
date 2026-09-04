package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.ItemPedido;

public class ItemPedidoDao {

    private static Session session;
    private Transaction tx;

    private static ItemPedidoDao dao = null;

    protected ItemPedidoDao() {
    }

    public static ItemPedidoDao getIntancia() {
        if (dao == null) {
            dao = new ItemPedidoDao();
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

    public int agregar(ItemPedido objeto) {
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

    public void actualizar(ItemPedido objeto) {
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

    public void eliminar(ItemPedido objeto) {
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

    public ItemPedido traerItemPedido(long id) {
        ItemPedido objeto = null;

        try {
            iniciaOperacion();

            objeto = (ItemPedido) session.get(ItemPedido.class, id);

        } finally {
            session.close();
        }

        return objeto;
    }

    public Set<ItemPedido> traerItemsPedido() {
        Set<ItemPedido> lista = null;

        try {
            iniciaOperacion();

            lista = new java.util.HashSet<>(
                session.createQuery(
                    "from ItemPedido",
                    ItemPedido.class
                ).getResultList()
            );

        } finally {
            session.close();
        }

        return lista;
    }
}