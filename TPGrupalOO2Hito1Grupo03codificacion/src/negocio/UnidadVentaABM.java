package negocio;
import dao.UnidadVentaDao;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.UnidadVentaDao;
import datos.FoodTrack;
import datos.UnidadVenta;

public class UnidadVentaABM {

	UnidadVentaDao dao = UnidadVentaDao.getIntancia();

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
	
	public Set<UnidadVenta> traerUnidadesVentaPorDatosStaff(String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso){
		return dao.traerUnidadesVentaPorDatosStaff(dni, fechaNacimiento, fechaIngreso);
	}
	public UnidadVenta traerUnidadVentaConPlatos(long idUnidadVenta) {
	    return dao.traerUnidadVentaConPlatos(idUnidadVenta);
	}
	public List<FoodTrack> traerFoodTracksConElectricidad(boolean requiereElectricidad) {
	    return dao.traerFoodTracksConElectricidad(requiereElectricidad);
	}
		public List<FoodTrack> traerFoodTracksConSuperficieMinima(double minSuperficie) {
	    return dao.traerFoodTracksPorSuperficieMinima(minSuperficie);
	}

	public List<UnidadVenta> traerUnidadesVentaConMinimoPedidos(long minPedidos) {
	    return dao.traerUnidadesVentaConMinimoPedidos(minPedidos);
	}
}
