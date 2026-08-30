package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResetBaseDao {

    public static void resetearBase() throws HibernateException {

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Primero tablas intermedias
            session.createSQLQuery("DELETE FROM festival_unidadventa").executeUpdate();
            session.createSQLQuery("DELETE FROM festival_staff").executeUpdate();
            session.createSQLQuery("DELETE FROM unidadventa_staff").executeUpdate();
            session.createSQLQuery("DELETE FROM unidadventa_plato").executeUpdate();

            // Tablas hijas
            session.createSQLQuery("DELETE FROM item_pedido").executeUpdate();
            session.createSQLQuery("DELETE FROM pedido").executeUpdate();
            session.createSQLQuery("DELETE FROM foodtrack").executeUpdate();
            session.createSQLQuery("DELETE FROM puestodesarmable").executeUpdate();
            session.createSQLQuery("DELETE FROM cocinero").executeUpdate();
            session.createSQLQuery("DELETE FROM cajero").executeUpdate();

            // Tablas principales
            session.createSQLQuery("DELETE FROM unidadventa").executeUpdate();
            session.createSQLQuery("DELETE FROM festival").executeUpdate();
            session.createSQLQuery("DELETE FROM plato").executeUpdate();
            session.createSQLQuery("DELETE FROM staff").executeUpdate();

            // Reset de IDs
            session.createSQLQuery("ALTER TABLE festival AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE staff AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE cocinero AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE cajero AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE plato AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE unidadventa AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE foodtrack AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE puestodesarmable AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE pedido AUTO_INCREMENT = 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE item_pedido AUTO_INCREMENT = 1").executeUpdate();

            tx.commit();

            System.out.println("BASE DE DATOS RESETEADA CORRECTAMENTE");

        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }

            throw e;

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
}