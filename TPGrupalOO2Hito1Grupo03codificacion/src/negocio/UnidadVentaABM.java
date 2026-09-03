package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.UnidadVentaDao;
import datos.FoodTrack;
import datos.UnidadVenta;

public class UnidadVentaABM {

    private static UnidadVentaABM instancia;
    private UnidadVentaDao dao = UnidadVentaDao.getIntancia();

    protected UnidadVentaABM() {}

    public static UnidadVentaABM getInstancia() {
        if (instancia == null) {
            instancia = new UnidadVentaABM();
        }
        return instancia;
    }

    public UnidadVenta traer(long idUnidadVenta) {
        return dao.traerUnidadVenta(idUnidadVenta);
    }

    public List<UnidadVenta> traer() {
        return dao.traerUnidadVentas();
    }

    public UnidadVenta traerConStaff(long idUnidadVenta) {
        return dao.traerUnidadVentaConStaff(idUnidadVenta);
    }

    public List<UnidadVenta> traerPorResponsable(long idStaff) {
        return dao.traerUnidadVentasPorResponsable(idStaff);
    }

    public Set<UnidadVenta> traerUnidadesVentaPorDatosStaff(String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        return dao.traerUnidadesVentaPorDatosStaff(dni, fechaNacimiento, fechaIngreso);
    }

    public UnidadVenta traerUnidadVentaConPlatos(long idUnidadVenta) {
        return dao.traerUnidadVentaConPlatos(idUnidadVenta);
    }

    public List<FoodTrack> traerFoodTracksConElectricidad(boolean requiereElectricidad) {
        return dao.traerFoodTracksConElectricidad(requiereElectricidad);
    }

    public Set<FoodTrack> traerFoodTracksConSuperficieMinima(double minSuperficie) {
        return dao.traerFoodTracksPorSuperficieMinima(minSuperficie);
    }

    public Set<UnidadVenta> traerUnidadesVentaConMinimoPedidos(long minPedidos) {
        return dao.traerUnidadesVentaConMinimoPedidos(minPedidos);
    }

    // --- Métodos de ABM agregados ---

    public long agregar(UnidadVenta u) throws Exception {
        return dao.agregar(u);
    }

    public void modificar(UnidadVenta u) throws Exception {
        dao.actualizar(u);
    }

    public void eliminar(long idUnidadVenta) throws Exception {
        UnidadVenta u = dao.traerUnidadVenta(idUnidadVenta);
        if (u == null) {
            throw new RuntimeException("ERROR: No existe unidad de venta con ID: " + idUnidadVenta);
        }
        dao.eliminar(u);
    }
}
