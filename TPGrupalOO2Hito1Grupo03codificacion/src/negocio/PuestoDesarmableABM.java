package negocio;

import java.util.HashSet;
import java.util.List;

import dao.PuestoDesarmableDao;
import dao.UnidadVentaDao;
import datos.Pedido;
import datos.Plato;
import datos.PuestoDesarmable;
import datos.Staff;
import datos.UnidadVenta;

public class PuestoDesarmableABM {

	PuestoDesarmableDao dao = PuestoDesarmableDao.getIntancia();
	UnidadVentaDao unidadVentaDao = UnidadVentaDao.getIntancia();

	public PuestoDesarmable traer(long idUnidadVenta) {
		return dao.traerPuestoDesarmable(idUnidadVenta);
	}

	public int agregar(String codigo, String nombreComercial, double superficieM2, Staff responsable,
			int cantidadCarpas, int tiempoMontajeMin) throws Exception {
		if (dao.traerPuestoDesarmablePorCodigo(codigo) != null) {
			throw new Exception("ERROR ya existe una unidad con codigo: " + codigo);
		}
		if (codigo == null || codigo.length() != 10) {
			throw new Exception("ERROR el codigo de unidad debe tener 10 caracteres");
		}
		PuestoDesarmable p = new PuestoDesarmable(null, codigo, nombreComercial, superficieM2,
				new HashSet<Pedido>(), new HashSet<Plato>(), new HashSet<Staff>(), responsable,
				cantidadCarpas, tiempoMontajeMin);
		return dao.agregar(p);
	}

	public void modificar(PuestoDesarmable p) throws Exception {
		dao.actualizar(p);
	}

	public void eliminar(long idUnidadVenta) throws Exception {
		PuestoDesarmable p = dao.traerPuestoDesarmable(idUnidadVenta);
		if (p == null) throw new RuntimeException("ERROR: No existe puesto desarmable con ID: " + idUnidadVenta);
		dao.eliminar(p);
	}

	public List<PuestoDesarmable> traer() {
		return dao.traerPuestosDesarmables();
	}

	public List<PuestoDesarmable> traerPorCantidadCarpasMinima(int minimoCarpas) {
		return dao.traerPuestosPorCantidadCarpasMinima(minimoCarpas);
	}

	public void asignarStaff(long idUnidadVenta, Staff staff) throws Exception {
	    UnidadVenta u = unidadVentaDao.traerUnidadVentaConStaff(idUnidadVenta);
	    
	    // Fallback: Si no recupera por la consulta con Fetch, trae la entidad directa
	    if (u == null) {
	        u = unidadVentaDao.traer(idUnidadVenta);
	    }
	    
	    if (u == null) {
	        throw new RuntimeException("ERROR: No existe unidad con ID: " + idUnidadVenta);
	    }
	    
	    u.asignarStaff(staff);
	    unidadVentaDao.actualizar(u);
	}
	public void ofrecerPlato(long idUnidadVenta, Plato plato) throws Exception {
		PuestoDesarmable p = dao.traerPuestoDesarmable(idUnidadVenta);
		if (p == null) throw new RuntimeException("ERROR: No existe unidad con ID: " + idUnidadVenta);
		p.getPlatosOfrecidos().add(plato);
		unidadVentaDao.actualizar(p);
	}
}
