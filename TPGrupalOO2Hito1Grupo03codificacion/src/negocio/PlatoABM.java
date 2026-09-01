package negocio;

import dao.PlatoDao;
import datos.Plato;

public class PlatoABM {

    private static PlatoABM instancia;
    private PlatoDao dao = PlatoDao.getIntancia();

    protected PlatoABM() {}

    public static PlatoABM getInstancia() {
        if (instancia == null) {
            instancia = new PlatoABM();
        }
        return instancia;
    }

    public Plato traer(long idPlato) {
        return dao.traerPlato(idPlato);
    }

    public Plato traer(String nombre) {
        return dao.traerPlato(nombre);
    }

    public int agregar(String nombre, double precioVenta, double costoProduccion) throws Exception {
        if (traer(nombre) != null) throw new Exception("ERROR ya existe un plato con nombre: " + nombre);
        Plato p = new Plato(null, nombre, precioVenta, costoProduccion);
        return dao.agregar(p);
    }

    public void modificar(Plato p) throws Exception {
        Plato existente = traer(p.getNombre());
        if (existente != null && existente.getId() != p.getId()) {
            throw new Exception("ERROR ya existe un plato con nombre: " + p.getNombre());
        }
        dao.actualizar(p);
    }

    public void eliminar(long idPlato) throws Exception {
        Plato p = dao.traerPlato(idPlato);
        if (p == null) throw new RuntimeException("ERROR: No existe plato con ID: " + idPlato);
        dao.eliminar(p);
    }
}