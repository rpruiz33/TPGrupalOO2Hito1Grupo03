package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.UnidadVentaDao;
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
}
